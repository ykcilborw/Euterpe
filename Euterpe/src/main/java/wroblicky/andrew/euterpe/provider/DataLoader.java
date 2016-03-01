package wroblicky.andrew.euterpe.provider;

import java.util.Properties;

import wroblicky.andrew.euterpe.Artist;
import wroblicky.andrew.euterpe.Play;
import wroblicky.andrew.euterpe.Song;
import wroblicky.andrew.euterpe.UpdateSet;
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
		insertUpdateSet(findAddedData(properties));
	}
	
	public UpdateSet findAddedData(Properties properties) {
		// TODO
		return null;
	}
	
	boolean doesArtistExist(String artistName) {
		return artistDAO.getArtistByName(artistName) != null;
	}
	
	boolean doesSongExist(Artist artist, String songName) {
		return songDAO.getSong(artist, songName) != null;
	}
	
	int getNewSongPlaysAmount(String songName, String artistName) {
		// TODO
		return 0;
	}
	
	void insertUpdateSet(UpdateSet updateSet) {
		
		if (updateSet != null) {
			// insert artists
			for (Artist artist : updateSet.getArtists()) {
				artistDAO.insertArtist(artist);
			}
			
			// insert songs
			for (Song song : updateSet.getSongs()) {
				songDAO.insertSong(song);
			}
			
			// insert plays
			for (Play play : updateSet.getPlays()) {
				playDAO.insertPlay(play);
			}
			
			// update all time play counts
			// TODO
			
			// other charts can be generated on the fly
		}
	}

}
