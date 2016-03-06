package wroblicky.andrew.euterpe.visualizer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import wroblicky.andrew.euterpe.Artist;
import wroblicky.andrew.euterpe.ChartCategory;
import wroblicky.andrew.euterpe.HistoricalChart;
import wroblicky.andrew.euterpe.Play;
import wroblicky.andrew.euterpe.Song;
import wroblicky.andrew.euterpe.TimeInterval;
import wroblicky.andrew.euterpe.TimeScope;
import wroblicky.andrew.euterpe.dao.ArtistDAO;
import wroblicky.andrew.euterpe.dao.DAOFactory;
import wroblicky.andrew.euterpe.dao.HistoricalChartDAO;
import wroblicky.andrew.euterpe.dao.PlayDAO;
import wroblicky.andrew.euterpe.dao.SongDAO;

public final class CommandLineVisualizer extends Visualizer {
	
	private ArtistDAO artistDAO;
	private SongDAO songDAO;
	private PlayDAO playDAO;
	private HistoricalChartDAO historicalChartDAO;
	private final Properties properties;
	
	public CommandLineVisualizer(DAOFactory daoFactory, Properties properties) {
		this.artistDAO = daoFactory.getArtistDAO();
		this.songDAO = daoFactory.getSongDAO();
		this.playDAO = daoFactory.getPlayDAO();
		this.historicalChartDAO = daoFactory.getHistoricalChartDAO();
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
		HistoricalChart historicalChart = historicalChartDAO.getHistoricalChart(timeInterval, timeScope, ChartCategory.SONG);
		StringBuilder toReturn = new StringBuilder();
		/*for (PlayHistory history : playHistories) {
			SongPlayHistory songHistory = (SongPlayHistory) history;
			toReturn.append(songHistory.getSongName());
			toReturn.append(",");
			toReturn.append(songHistory.getArtist());
			toReturn.append(",");
			toReturn.append(songHistory.getNumPlays());
		    toReturn.append("\n");
		}*/
		return toReturn.toString();
	}

	public String getArtistRankings(TimeInterval timeInterval,
			TimeScope timeScope) {
		HistoricalChart historicalChart = historicalChartDAO.getHistoricalChart(timeInterval, timeScope, ChartCategory.SONG);
		StringBuilder toReturn = new StringBuilder();
		/*for (PlayHistory history : playHistories) {
			toReturn.append(history.getArtist());
			toReturn.append(",");
			toReturn.append(history.getNumPlays());
		    toReturn.append("\n");
		}*/
		return toReturn.toString();
	}
	
	public String getPlays() {
		List<Play> plays = playDAO.getPlays();
		StringBuilder toReturn = new StringBuilder();
		for (Play play : plays) {
			toReturn.append(play.getSong().getName());
			toReturn.append(",");
			toReturn.append(play.getSong().getArtist().getName());
		    toReturn.append(",");
			toReturn.append(play.getTimestamp());
			toReturn.append("\n");
		}
		return toReturn.toString();
	}

	public String getSongs() {
		Set<Song> songs = songDAO.getSongs();
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
		Set<Artist> artists = artistDAO.getArtists();
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
