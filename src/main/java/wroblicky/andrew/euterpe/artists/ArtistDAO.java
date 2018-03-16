package wroblicky.andrew.euterpe.artists;

import java.util.Set;

public interface ArtistDAO {
	
	void createArtistTable();
	
	void insertArtist(String artist);
	
	Set<Artist> getArtists();
	
	Artist getArtist(String name);
	
	void dropArtists();

}
