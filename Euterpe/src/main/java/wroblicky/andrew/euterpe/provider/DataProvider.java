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

public abstract class DataProvider {
	
	private ArtistDAO artistDAO;
	private SongDAO songDAO;
	private PlayDAO playDAO;
	private Properties properties;
	
	DataProvider(DAOFactory daoFactory, Properties properties) {
		this.artistDAO = daoFactory.getArtistDAO();
		this.songDAO = daoFactory.getSongDAO();
		this.playDAO = daoFactory.getPlayDAO();
		this.properties = properties;
	}
	
	public void fetchAndInsertLatestData() {
		insertUpdateSet(findAddedData(properties));
	}
	
	abstract UpdateSet findAddedData(Properties properties);
	
	boolean doesArtistExist(String artistName) {
		if (artistDAO.getArtistByName(artistName) != null) {
			return true;
		}
		return false;
	}
	
	boolean doesSongExist(Artist artist, String songName) {
		if (songDAO.getSong(artist, songName) != null) {
			return true;
		}
		return false;
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
