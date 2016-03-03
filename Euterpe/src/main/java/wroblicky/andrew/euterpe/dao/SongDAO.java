package wroblicky.andrew.euterpe.dao;

import java.util.Set;

import wroblicky.andrew.euterpe.Artist;
import wroblicky.andrew.euterpe.Song;

public interface SongDAO {
	
	void createSongTable();
	
	void insertSong(Song song);
	
	Set<Song> getSongs();
	
	Song getSong(Artist artist, String name);
	
	void dropSongs();

}
