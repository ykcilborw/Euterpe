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

import wroblicky.andrew.euterpe.artists.Artist;
import wroblicky.andrew.euterpe.charts.ChartCategory;
import wroblicky.andrew.euterpe.charts.ChartEntry;
import wroblicky.andrew.euterpe.plays.Play;
import wroblicky.andrew.euterpe.songs.Song;
import wroblicky.andrew.euterpe.charts.TimeInterval;
import wroblicky.andrew.euterpe.charts.TimeScope;
import wroblicky.andrew.euterpe.artists.ArtistDAO;
import wroblicky.andrew.euterpe.dao.DAOFactory;
import wroblicky.andrew.euterpe.charts.HistoricalChartDAO;
import wroblicky.andrew.euterpe.plays.PlayDAO;
import wroblicky.andrew.euterpe.songs.SongDAO;
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
		Function<Song, String> function = new Function<Song, String>() {
			@Override
			public String apply(Song inputSong) {
				return inputSong.getPersistentID();
			}
		};
		Set<String> existingSongs = Sets.newHashSet(Iterables
				.transform(songDAO.getSongs(), function));
		Set<String> importedSongs = musicLibrary.getSongLookup()
				.keySet();
		Set<String> newSongs = Sets.difference(existingSongs,
				importedSongs);
		for (String key : newSongs) {
			InputSong inputSong = musicLibrary.getSongLookup().get(key);
			Song song = SongMapper.from(inputSong,
					artistDAO.getArtist(inputSong.getArtist()));
			songDAO.insertSong(song);
		}
	}

	private void insertNewPlays(MusicLibrary musicLibrary) {
		Map<String,InputSong> songLookup = musicLibrary.getSongLookup();
		Map<String, Integer> importedSongsToPlays = musicLibrary
				.getPlayCountLookup();
		List<ChartEntry> chartEntries = historicalChartDAO.getHistoricalChart(
				TimeInterval.ALL_TIME, TimeScope.ALL_TIME, ChartCategory.SONG)
				.getResults();
		Map<String, Integer> existingSongsToPlays = new HashMap<>();
		for (ChartEntry chartEntry : chartEntries) {
			Song song = songDAO.getSong(chartEntry.getID());
			existingSongsToPlays.put(song.getPersistentID(),
					chartEntry.getNumPlays());
		}

		MapDifference<String, Integer> differenceMap = Maps
				.difference(importedSongsToPlays, existingSongsToPlays);
		Map<String, ValueDifference<Integer>> differingValuesMap = differenceMap.entriesDiffering();
		for (String key : differingValuesMap.keySet()) {
			ValueDifference<Integer> valueDifference = differingValuesMap.get(key);
			int amountDifference = ((Integer) valueDifference.leftValue()) - ((Integer) valueDifference.rightValue());
			while (amountDifference > 0) {
				playDAO.insertPlay(new Play(songDAO.getSong(key), songLookup.get(key).getMostRecentPlayDate()));
			}
		}
	}
}
