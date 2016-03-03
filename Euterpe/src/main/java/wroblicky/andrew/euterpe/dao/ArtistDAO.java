package wroblicky.andrew.euterpe.dao;

import java.util.List;

import wroblicky.andrew.euterpe.Artist;

public interface ArtistDAO {
	
	public void createArtistTable();
	
	public void insertArtist(Artist artist);
	
	public List<Artist> getArtists();
	
	public Artist getArtistByName(String name);
	
	public void dropArtists();

}
