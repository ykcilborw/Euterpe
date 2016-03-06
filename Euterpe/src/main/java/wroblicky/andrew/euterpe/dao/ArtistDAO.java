package wroblicky.andrew.euterpe.dao;

import java.util.Set;

import wroblicky.andrew.euterpe.Artist;

public interface ArtistDAO {
	
	void createArtistTable();
	
	void insertArtist(String artist);
	
	Set<Artist> getArtists();
	
	Artist getArtist(String name);
	
	void dropArtists();

}
