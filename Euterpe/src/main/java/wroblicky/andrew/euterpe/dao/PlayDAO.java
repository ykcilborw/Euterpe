package wroblicky.andrew.euterpe.dao;

import java.util.List;
import java.util.Set;

import wroblicky.andrew.euterpe.Artist;
import wroblicky.andrew.euterpe.Play;
import wroblicky.andrew.euterpe.Song;

public interface PlayDAO {
	
	void createPlayTable();
	
	void insertPlay(int songId, long timestamp);
	
	void insertPlay(Play play);
	
	List<Play> getPlays();
	
	Set<Play> getPlays(Artist artist);
	
	Set<Play> getPlays(Song song);
	
	void dropPlays();

}
