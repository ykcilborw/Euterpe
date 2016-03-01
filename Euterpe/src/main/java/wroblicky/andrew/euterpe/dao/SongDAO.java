package wroblicky.andrew.euterpe.dao;

import java.util.List;

import wroblicky.andrew.euterpe.Artist;
import wroblicky.andrew.euterpe.PlayHistory;
import wroblicky.andrew.euterpe.Song;
import wroblicky.andrew.euterpe.TimeInterval;
import wroblicky.andrew.euterpe.TimeScope;

public interface SongDAO {
	
	public abstract List<PlayHistory> getSongRankings(
			TimeInterval timeInterval, TimeScope timeScope);
	
	public abstract List<PlayHistory> getSongRankings(
			TimeInterval timeInterval, TimeScope timeScope, int start, int end);
	
	public abstract List<Song> getSongs();
	
	public abstract Song getSong(Artist artist, String name);
	
	public abstract void insertSong(Song song);
	
	public abstract void createSongTable();
	
	public abstract void dropSongs();

}
