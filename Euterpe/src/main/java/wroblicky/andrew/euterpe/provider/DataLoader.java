package wroblicky.andrew.euterpe.provider;

import java.util.Properties;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

import wroblicky.andrew.euterpe.Artist;
import wroblicky.andrew.euterpe.Song;
import wroblicky.andrew.euterpe.dao.ArtistDAO;
import wroblicky.andrew.euterpe.dao.DAOFactory;
import wroblicky.andrew.euterpe.dao.PlayDAO;
import wroblicky.andrew.euterpe.dao.SongDAO;
import wroblicky.andrew.euterpe.mapper.SongMapper;

public class DataLoader {
	
	private final MusicLibraryProvider musicLibraryProvider;
	private final ArtistDAO artistDAO;
	private final SongDAO songDAO;
	private final PlayDAO playDAO;
	private final Properties properties;
	
	public DataLoader(MusicLibraryProvider musicLibraryProvider, DAOFactory daoFactory, Properties properties) {
		this.musicLibraryProvider = musicLibraryProvider;
		this.artistDAO = daoFactory.getArtistDAO();
		this.songDAO = daoFactory.getSongDAO();
		this.playDAO = daoFactory.getPlayDAO();
		this.properties = properties;
	}
	
	public void fetchAndInsertLatestData() {
		MusicLibrary musicLibrary = musicLibraryProvider.getMusicLibrary(properties);
		
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
				return new SongIdentificationKey(inputSong.getArtist().getName(), inputSong.getName(), inputSong.getDateAdded());
			}
		};
		Set<SongIdentificationKey> existingSongs = Sets.newHashSet(Iterables.transform(
				songDAO.getSongs(), function));
		Set<SongIdentificationKey> importedSongs = musicLibrary.getSongLookup().keySet();
		Set<SongIdentificationKey> newSongs = Sets.difference(existingSongs,
				importedSongs);
		for (SongIdentificationKey key : newSongs) {
			InputSong inputSong = musicLibrary.getSongLookup().get(key);
			Song song = SongMapper.from(inputSong, artistDAO.getArtistByName(inputSong.getArtist()));
			songDAO.insertSong(song);
		}
	}
	
	private void insertNewPlays(MusicLibrary musicLibrary) {
		Set<Song> existingArtists = songDAO.getSongs();
	}
	
	private Set<SongIdentificationKey> generateSongIdentificationKeys(Set<Song> songs) {
		return null;
	}
}
