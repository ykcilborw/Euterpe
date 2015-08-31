package wroblicky.andrew.euterpe;

import java.util.List;

public abstract class DatabaseManager {

	public abstract List<PlayHistory> getSongRankings(
			TimeInterval timeInterval, TimeScope timeScope);
	
	public abstract List<PlayHistory> getSongRankings(
			TimeInterval timeInterval, TimeScope timeScope, int start, int end);
	
	public abstract List<PlayHistory> getArtistRankings(
			TimeInterval timeInterval, TimeScope timeScope);
	
	public abstract List<PlayHistory> getArtistRankings(
			TimeInterval timeInterval, TimeScope timeScope, int start, int end);

	public abstract List<Play> getPlays();
	
	public abstract List<Song> getSongs();
	
	public abstract Song getSong(Artist artist, String name);
	
	public abstract List<Artist> getArtists();
	
	public abstract Artist getArtistByName(String name);
	
	public abstract void insertPlay(Play play);
	
	public abstract void insertSong(Song song);
	
	public abstract void insertArtist(Artist artist);
	
	public abstract void insertPlayHistory(PlayHistory playHistory);
	
	public abstract void updatePlayHistory(PlayHistory playHistory);
	
	public abstract void createPlayTable();
	
	public abstract void createSongTable();
	
	public abstract void createArtistTable();
	
	public abstract void createSongPlayHistoryTables();
	
	public abstract void createArtistPlayHistoryTables();
	
	public abstract void dropPlays();
	
	public abstract void dropSongs();
	
	public abstract void dropArtists();
	
	public abstract void dropSongPlayHistoryTables();
	
	public abstract void dropArtistPlayHistoryTables();
}
