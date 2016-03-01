package wroblicky.andrew.euterpe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import wroblicky.andrew.euterpe.Artist;
import wroblicky.andrew.euterpe.PlayHistory;
import wroblicky.andrew.euterpe.Song;
import wroblicky.andrew.euterpe.TimeInterval;
import wroblicky.andrew.euterpe.TimeScope;

public class SongDAOImpl implements SongDAO {
	
	private String databaseName;

	@Override
	public List<PlayHistory> getSongRankings(TimeInterval timeInterval,
			TimeScope timeScope) {
		return null;
	}

	@Override
	public List<PlayHistory> getSongRankings(TimeInterval timeInterval,
			TimeScope timeScope, int start, int end) {
		return null;
	}

	@Override
	public List<Song> getSongs() {
		Connection c = null;
	    Statement stmt = null;
	    List<Song> songs = new ArrayList<Song>();
	    try {
	      c = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM SONGS JOIN ARTISTS WHERE songs.artist_id = artists.id ;" );

	      while ( rs.next() ) {
	         int id = rs.getInt("id");
	         String songName = rs.getString("songs.name");
	         int artistId = rs.getInt("artists.id");
	         String artistName  = rs.getString("artists.name");
	         Artist artist = new Artist(artistId, artistName);
	         Long dateAdded = rs.getLong("date_added");
	         songs.add(new Song(id, songName, artist, dateAdded));
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Operation done successfully");
	    return songs;
	}

	@Override
	public Song getSong(Artist artist, String name) {
		return null;
	}

	@Override
	public void insertSong(Song song) {
	    Connection c = null;
	    Statement stmt = null;
	    try {
	      c = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO SONGS (NAME,ARTIST_ID,DATE_ADDED) "
					+ "VALUES ('" + song.getName() + "',"
					+ song.getArtist().getID() + "," + song.getDateAdded()
					+ ");";
			stmt.executeUpdate(sql);

	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Song record created successfully");
	}

	@Override
	public void createSongTable() {
		Connection c = null;
		Statement stmt = null;
		try {
			c = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE songs "
					+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " NAME           TEXT    NOT NULL,"
					+ " ARTIST_ID      INTEGER     NOT NULL,"
					+ " DATE_ADDED     TIMESTAMP,"
					+ " FOREIGN KEY(ARTIST_ID) REFERENCES ARTISTS(ID))";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Songs table created successfully");
		
	}

	@Override
	public void dropSongs() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "DROP TABLE IF EXISTS songs; ";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Song table dropped successfully");
	}

}
