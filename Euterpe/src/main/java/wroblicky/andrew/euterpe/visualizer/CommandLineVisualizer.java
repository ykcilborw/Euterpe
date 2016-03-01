package wroblicky.andrew.euterpe.visualizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;

import wroblicky.andrew.euterpe.Artist;
import wroblicky.andrew.euterpe.Play;
import wroblicky.andrew.euterpe.PlayHistory;
import wroblicky.andrew.euterpe.Song;
import wroblicky.andrew.euterpe.SongPlayHistory;
import wroblicky.andrew.euterpe.TimeInterval;
import wroblicky.andrew.euterpe.TimeScope;
import wroblicky.andrew.euterpe.dao.DatabaseManager;

public final class CommandLineVisualizer extends Visualizer {
	
	private final DatabaseManager databaseManager;
	private final Properties properties;
	
	public CommandLineVisualizer(DatabaseManager databaseManager, Properties properties) {
		this.databaseManager = databaseManager;
		this.properties = properties;
	}
	
	private enum ActionType {
		GET_SONG_RANKINGS, GET_ARTIST_RANKINGS, GET_PLAYS, GET_SONGS, GET_ARTISTS;
	}
	
	@Override
	public void launch() {
		System.out.println("Welcome to the command line version of Euterpe!");
		System.out.println("Please specify what you which action you would like to perform and optionally the time interval");
		
		try{
			BufferedReader br = 
	                      new BufferedReader(new InputStreamReader(System.in));
				
			String input;
				
			while((input=br.readLine())!=null) {
				ActionType actionType = ActionType.GET_ARTISTS;
				TimeInterval timeInterval = TimeInterval.ALL_TIME;
				TimeScope timeScope = TimeScope.WITHIN_RANGE;
				String[] inputPieces = input.split(" ");
				try {
					actionType = ActionType.valueOf(inputPieces[0].toUpperCase());
					if (inputPieces.length > 1) {
						timeInterval = TimeInterval.valueOf(inputPieces[1].toUpperCase());
						timeScope = TimeScope.valueOf(inputPieces[2].toUpperCase());
					}
				} catch (IllegalArgumentException e) {
					System.out.println("The provided action type is not supported. "
							+ "Allowed types are "
							+ "GET_SONG_RANKINGS, GET_ARTIST_RANKINGS, GET_PLAYS, GET_SONGS, and GET_ARTISTS");
				}
				switch (actionType) {
				case GET_SONG_RANKINGS:	System.out.println(getSongRankings(timeInterval, timeScope));
										break;
				case GET_ARTIST_RANKINGS: System.out.println(getArtistRankings(timeInterval, timeScope));
				                          break;
				case GET_PLAYS: System.out.println(getPlays());
				                break;
				case GET_SONGS: System.out.println(getSongs());
				                break;
				case GET_ARTISTS: System.out.println(getArtists());
				                  break;
					default: System.out.println("Unsupported action type: " + actionType);
				}
			}
				
		} catch (IOException io){
			io.printStackTrace();
		}	
	}

	
	public String getSongRankings(TimeInterval timeInterval,
			TimeScope timeScope) {
		List<PlayHistory> playHistories = databaseManager.getSongRankings(timeInterval, timeScope);
		StringBuilder toReturn = new StringBuilder();
		for (PlayHistory history : playHistories) {
			SongPlayHistory songHistory = (SongPlayHistory) history;
			toReturn.append(songHistory.getSongName());
			toReturn.append(",");
			toReturn.append(songHistory.getArtist());
			toReturn.append(",");
			toReturn.append(songHistory.getNumPlays());
		    toReturn.append("\n");
		}
		return toReturn.toString();
	}

	public String getArtistRankings(TimeInterval timeInterval,
			TimeScope timeScope) {
		List<PlayHistory> playHistories = databaseManager.getArtistRankings(timeInterval, timeScope);
		StringBuilder toReturn = new StringBuilder();
		for (PlayHistory history : playHistories) {
			toReturn.append(history.getArtist());
			toReturn.append(",");
			toReturn.append(history.getNumPlays());
		    toReturn.append("\n");
		}
		return toReturn.toString();
	}
	
	public String getPlays() {
		List<Play> plays = databaseManager.getPlays();
		StringBuilder toReturn = new StringBuilder();
		for (Play play : plays) {
			toReturn.append(play.getSong().getName());
			toReturn.append(",");
			toReturn.append(play.getArtist().getName());
		    toReturn.append(",");
			toReturn.append(play.getTimestamp());
			toReturn.append("\n");
		}
		return toReturn.toString();
	}

	public String getSongs() {
		List<Song> songs = databaseManager.getSongs();
		StringBuilder toReturn = new StringBuilder();
		for (Song song : songs) {
			toReturn.append(song.getID());
			toReturn.append(",");
			toReturn.append(song.getName());
		    toReturn.append(",");
			toReturn.append(song.getArtist());
		    toReturn.append(",");
			toReturn.append(song.getDateAdded());
			toReturn.append("\n");
		}
		return toReturn.toString();
	}

	public String getArtists() {
		List<Artist> artists = databaseManager.getArtists();
		StringBuilder toReturn = new StringBuilder();
		for (Artist artist : artists) {
			toReturn.append(artist.getID());
			toReturn.append(",");
			toReturn.append(artist.getName());
			toReturn.append("\n");
		}
		return toReturn.toString();
	}

}
