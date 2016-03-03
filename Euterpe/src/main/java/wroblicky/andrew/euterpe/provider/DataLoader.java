package wroblicky.andrew.euterpe.provider;

import java.util.Properties;
import java.util.Set;

import wroblicky.andrew.euterpe.Artist;
import wroblicky.andrew.euterpe.Play;
import wroblicky.andrew.euterpe.Song;
import wroblicky.andrew.euterpe.dao.ArtistDAO;
import wroblicky.andrew.euterpe.dao.DAOFactory;
import wroblicky.andrew.euterpe.dao.PlayDAO;
import wroblicky.andrew.euterpe.dao.SongDAO;

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
		Set<Artist> existingArtists = artistDAO.getArtists();
	}
	
	private void insertNewSongs(MusicLibrary musicLibrary) {
		Set<Song> existingArtists = songDAO.getSongs();
	}
	
	private void insertNewPlays(MusicLibrary musicLibrary) {
		Set<Song> existingArtists = songDAO.getSongs();
	}
	
	private Set<SongIdentificationKey> generateSongIdentificationKeys(Set<Song> songs) {
		return null;
	}
}
