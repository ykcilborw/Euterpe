package wroblicky.andrew.euterpe.dao;

import java.util.List;

import wroblicky.andrew.euterpe.Artist;
import wroblicky.andrew.euterpe.PlayHistory;
import wroblicky.andrew.euterpe.TimeInterval;
import wroblicky.andrew.euterpe.TimeScope;

public interface ArtistDAO {
	
	public abstract List<PlayHistory> getArtistRankings(
			TimeInterval timeInterval, TimeScope timeScope);
	
	public abstract List<PlayHistory> getArtistRankings(
			TimeInterval timeInterval, TimeScope timeScope, int start, int end);
	
	public abstract List<Artist> getArtists();
	
	public abstract Artist getArtistByName(String name);
	
	public abstract void insertArtist(Artist artist);
	
	public abstract void createArtistTable();
	
	public abstract void dropArtists();

}
