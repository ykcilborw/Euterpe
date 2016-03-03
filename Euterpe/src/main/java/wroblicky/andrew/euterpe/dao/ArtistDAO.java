package wroblicky.andrew.euterpe.dao;

import java.util.List;

import wroblicky.andrew.euterpe.Artist;

public interface ArtistDAO {
	
	void createArtistTable();
	
	void insertArtist(Artist artist);
	
	List<Artist> getArtists();
	
	Artist getArtistByName(String name);
	
	void dropArtists();

}
