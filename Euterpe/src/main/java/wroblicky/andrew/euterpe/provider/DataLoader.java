package wroblicky.andrew.euterpe.provider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.MapDifference;
import com.google.common.collect.MapDifference.ValueDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import wroblicky.andrew.euterpe.Artist;
import wroblicky.andrew.euterpe.ChartCategory;
import wroblicky.andrew.euterpe.ChartEntry;
import wroblicky.andrew.euterpe.Song;
import wroblicky.andrew.euterpe.TimeInterval;
import wroblicky.andrew.euterpe.TimeScope;
import wroblicky.andrew.euterpe.dao.ArtistDAO;
import wroblicky.andrew.euterpe.dao.DAOFactory;
import wroblicky.andrew.euterpe.dao.HistoricalChartDAO;
import wroblicky.andrew.euterpe.dao.PlayDAO;
import wroblicky.andrew.euterpe.dao.SongDAO;
import wroblicky.andrew.euterpe.mapper.SongMapper;

public class DataLoader {

	private final MusicLibraryProvider musicLibraryProvider;
	private final ArtistDAO artistDAO;
	private final SongDAO songDAO;
	private final PlayDAO playDAO;
	private final HistoricalChartDAO historicalChartDAO;
	private final Properties properties;

	public DataLoader(MusicLibraryProvider musicLibraryProvider,
			DAOFactory daoFactory, Properties properties) {
		this.musicLibraryProvider = musicLibraryProvider;
		this.artistDAO = daoFactory.getArtistDAO();
		this.songDAO = daoFactory.getSongDAO();
		this.playDAO = daoFactory.getPlayDAO();
		this.historicalChartDAO = daoFactory.getHistoricalChartDAO();
		this.properties = properties;
	}

	public void fetchAndInsertLatestData() {
		MusicLibrary musicLibrary = musicLibraryProvider
				.getMusicLibrary(properties);

		// check new artists
		insertNewArtists(musicLibrary);

		// check new songs
		insertNewSongs(musicLibrary);

		// check new plays
		insertNewPlays(musicLibrary);
	}

	private void insertNewArtists(MusicLibrary musicLibrary) {
		Function<Artist, String> function = new Function<Artist, String>() {
			@Override
			public String apply(Artist artist) {
				return artist.getName();
			}
		};
		Set<String> existingArtists = Sets.newHashSet(Iterables.transform(
				artistDAO.getArtists(), function));
		Set<String> importedArtists = musicLibrary.getArtistNames();
		Set<String> newArtists = Sets.difference(existingArtists,
				importedArtists);
		for (String artist : newArtists) {
			artistDAO.insertArtist(artist);
		}
	}

	private void insertNewSongs(MusicLibrary musicLibrary) {
		Function<Song, SongIdentificationKey> function = new Function<Song, SongIdentificationKey>() {
			@Override
			public SongIdentificationKey apply(Song inputSong) {
				return new SongIdentificationKey(inputSong.getArtist()
						.getName(), inputSong.getName(),
						inputSong.getDateAdded());
			}
		};
		Set<SongIdentificationKey> existingSongs = Sets.newHashSet(Iterables
				.transform(songDAO.getSongs(), function));
		Set<SongIdentificationKey> importedSongs = musicLibrary.getSongLookup()
				.keySet();
		Set<SongIdentificationKey> newSongs = Sets.difference(existingSongs,
				importedSongs);
		for (SongIdentificationKey key : newSongs) {
			InputSong inputSong = musicLibrary.getSongLookup().get(key);
			Song song = SongMapper.from(inputSong,
					artistDAO.getArtistByName(inputSong.getArtist()));
			songDAO.insertSong(song);
		}
	}

	private void insertNewPlays(MusicLibrary musicLibrary) {
		Map<SongIdentificationKey,InputSong> songLookup = musicLibrary.getSongLookup();
		Map<SongIdentificationKey, Integer> importedSongsToPlays = musicLibrary
				.getPlayCountLookup();
		List<ChartEntry> chartEntries = historicalChartDAO.getHistoricalChart(
				TimeInterval.ALL_TIME, TimeScope.ALL_TIME, ChartCategory.SONG)
				.getResults();
		Map<SongIdentificationKey, Integer> existingSongsToPlays = new HashMap<>();
		for (ChartEntry chartEntry : chartEntries) {
			Song song = songDAO.getSong(chartEntry.getID());
			existingSongsToPlays.put(SongMapper.from(song),
					chartEntry.getNumPlays());
		}

		MapDifference<SongIdentificationKey, Integer> differenceMap = Maps
				.difference(importedSongsToPlays, existingSongsToPlays);
		Map<SongIdentificationKey, ValueDifference<Integer>> differingValuesMap = differenceMap.entriesDiffering();
		for (SongIdentificationKey key : differingValuesMap.keySet()) {
			ValueDifference<Integer> valueDifference = differingValuesMap.get(key);
			int amountDifference = ((Integer) valueDifference.leftValue()) - ((Integer) valueDifference.rightValue());
			while (amountDifference > 0) {
				playDAO.insertPlay(songDAO.getSong(key).getID(), songLookup.get(key).getMostRecentPlayDate());
			}
		}
	}
}
