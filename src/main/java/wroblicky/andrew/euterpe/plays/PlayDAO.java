package wroblicky.andrew.euterpe.plays;

import java.util.List;
import java.util.Set;

import wroblicky.andrew.euterpe.artists.Artist;
import wroblicky.andrew.euterpe.songs.Song;

public interface PlayDAO {
	
	void createPlayTable();
	
	void insertPlay(Play play);
	
	List<Play> getPlays();
	
	Set<Play> getPlays(Artist artist);
	
	Set<Play> getPlays(Song song);
	
	void dropPlays();

}
