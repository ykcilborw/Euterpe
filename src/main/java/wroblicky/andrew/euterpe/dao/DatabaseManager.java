package wroblicky.andrew.euterpe.dao;

import wroblicky.andrew.euterpe.plays.PlayHistory;

public interface DatabaseManager {

	public abstract void insertPlayHistory(PlayHistory playHistory);
	
	public abstract void updatePlayHistory(PlayHistory playHistory);
	
	public abstract void createSongPlayHistoryTables();
	
	public abstract void createArtistPlayHistoryTables();
	
	public abstract void dropSongPlayHistoryTables();
	
	public abstract void dropArtistPlayHistoryTables();
}
