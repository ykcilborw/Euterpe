package wroblicky.andrew.euterpe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import wroblicky.andrew.euterpe.Artist;
import wroblicky.andrew.euterpe.Play;
import wroblicky.andrew.euterpe.Song;

public class PlayDAOImpl implements PlayDAO {
	
	private String databaseName;
	
	@Override
	public void createPlayTable() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE plays "
					+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " SONG_ID INTEGER NOT NULL," + " ARTIST_ID INT NOT NULL,"
					+ " TIMESTAMP TIMESTAMP NOT NULL,"
					+ " FOREIGN KEY(SONG_ID) REFERENCES SONGS(ID),"
					+ " FOREIGN KEY(ARTIST_ID) REFERENCES ARTISTS(ID))";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Plays table created successfully");
	}
	
	@Override
	public void insertPlay(Play play) {
	    Connection c = null;
	    Statement stmt = null;
	    try {
	      c = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO PLAYS (SONG_ID,ARTIST_ID,TIMESTAMP) "
					+ "VALUES (" + play.getSong().getID() + ","
					+ play.getArtist().getID() + "," + play.getTimestamp()
					+ ");";
			stmt.executeUpdate(sql);

	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Play record created successfully");
	}

	@Override
	public List<Play> getPlays() {
		Connection c = null;
	    Statement stmt = null;
	    List<Play> plays = new ArrayList<Play>();
	    try {
	      c = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM PLAYS JOIN SONGS, ARTISTS WHERE songs.id = plays.song_id and artists.id = plays.artist_id ;" );

	      while ( rs.next() ) {
	         int id = rs.getInt("songs.id");
	         String songName = rs.getString("songs.name");
	         int artistId = rs.getInt("artists.id");
	         String artistName  = rs.getString("artists.name");
	         Artist artist = new Artist(artistId, artistName);
	         Long dateAdded = rs.getLong("date_added");
	         Song song = new Song(id, songName, artist, dateAdded);
	         plays.add(new Play(song, artist, rs.getLong("plays.timestamp")));
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Operation done successfully");
	    return plays;
	}

	@Override
	public void dropPlays() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "DROP TABLE IF EXISTS plays; ";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Play table dropped successfully");
	}
}
