package wroblicky.andrew.euterpe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import wroblicky.andrew.euterpe.Artist;
import wroblicky.andrew.euterpe.Play;
import wroblicky.andrew.euterpe.PlayHistory;
import wroblicky.andrew.euterpe.Song;
import wroblicky.andrew.euterpe.TimeInterval;
import wroblicky.andrew.euterpe.TimeScope;

public class SqliteDatabaseManager extends DatabaseManager {
	
	private String databaseName;
	
	public SqliteDatabaseManager(String databaseName) {
		this.databaseName = databaseName;
	}

	@Override
	public List<PlayHistory> getSongRankings(TimeInterval timeInterval,
			TimeScope timeScope) {
		return null;
	}

	@Override
	public List<PlayHistory> getSongRankings(TimeInterval timeInterval,
			TimeScope timeScope, int start, int end) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlayHistory> getArtistRankings(TimeInterval timeInterval,
			TimeScope timeScope) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlayHistory> getArtistRankings(TimeInterval timeInterval,
			TimeScope timeScope, int start, int end) {
		// TODO Auto-generated method stub
		return null;
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
		Song song = null;
				
		return song;
	}

	@Override
	public List<Artist> getArtists() {
		Connection c = null;
	    Statement stmt = null;
	    List<Artist> artists = new ArrayList<Artist>();
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM ARTISTS;" );

	      while ( rs.next() ) {
	         int artistId = rs.getInt("id");
	         String artistName  = rs.getString("name");
	         artists.add(new Artist(artistId, artistName));
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Operation done successfully");
	    return artists;
	}
	
	@Override
	public Artist getArtistByName(String name) {
		Connection c = null;
	    Statement stmt = null;
	    Artist artist = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

	      stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM ARTISTS where name = " + name + ";" );

	      while ( rs.next() ) {
	         int artistId = rs.getInt("artists.id");
	         String artistName  = rs.getString("artists.name");
	         artist = new Artist(artistId, artistName);
	         break;
	      }
	      rs.close();
	      stmt.close();
	      c.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Operation done successfully");
	    return artist;
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
	public void insertArtist(Artist artist) {
	    Connection c = null;
	    Statement stmt = null;
	    try {
	      c = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
	      c.setAutoCommit(false);
	      System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "INSERT INTO ARTISTS (NAME) "
					+ "VALUES ('" + artist.getName() + "');";
			stmt.executeUpdate(sql);

	      stmt.close();
	      c.commit();
	      c.close();
	    } catch ( Exception e ) {
	      e.printStackTrace();
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Artist record created successfully");
	}

	@Override
	public void insertPlayHistory(PlayHistory playHistory) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePlayHistory(PlayHistory playHistory) {

	}

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
	public void createArtistTable() {
		Connection c = null;
		Statement stmt = null;
		try {
			c = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE artists "
					+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " NAME           TEXT    NOT NULL)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Artists table created successfully");
	}

	@Override
	public void createSongPlayHistoryTables() {
		Connection c = null;
		Statement stmt = null;
		try {
			c = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE song_day_plays "
					+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " SONG_ID INTEGER    NOT NULL," + " ARTIST_ID INTEGER NOT NULL,"
					+ " START_TIME TIMESTAMP NOT NULL," + " END_TIME TIMESTAMP NOT NULL,"
					+ " NUM_PLAYS INT NOT NULL," + " PERCENT_CHANGE REAL,"
					+ " HEATING_UP TEXT NOT NULL,"
					+ " FOREIGN KEY(SONG_ID) REFERENCES SONGS(ID),"
					+ " FOREIGN KEY(ARTIST_ID) REFERENCES ARTISTS(ID))";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE song_week_plays "
					+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " SONG_ID INTEGER    NOT NULL," + " ARTIST_ID INTEGER NOT NULL,"
					+ " START_TIME TIMESTAMP NOT NULL," + " END_TIME TIMESTAMP NOT NULL,"
					+ " NUM_PLAYS INT NOT NULL," + " PERCENT_CHANGE REAL,"
					+ " HEATING_UP TEXT NOT NULL,"
					+ " FOREIGN KEY(SONG_ID) REFERENCES SONGS(ID),"
					+ " FOREIGN KEY(ARTIST_ID) REFERENCES ARTISTS(ID))";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE song_month_plays "
					+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " SONG_ID INTEGER    NOT NULL," + " ARTIST_ID INTEGER NOT NULL,"
					+ " START_TIME TIMESTAMP NOT NULL," + " END_TIME TIMESTAMP NOT NULL,"
					+ " NUM_PLAYS INT NOT NULL," + " PERCENT_CHANGE REAL,"
					+ " HEATING_UP TEXT NOT NULL,"
					+ " FOREIGN KEY(SONG_ID) REFERENCES SONGS(ID),"
					+ " FOREIGN KEY(ARTIST_ID) REFERENCES ARTISTS(ID))";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE song_year_plays "
					+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " SONG_ID INTEGER    NOT NULL," + " ARTIST_ID INTEGER NOT NULL,"
					+ " START_TIME TIMESTAMP NOT NULL," + " END_TIME TIMESTAMP NOT NULL,"
					+ " NUM_PLAYS INT NOT NULL," + " PERCENT_CHANGE REAL,"
					+ " HEATING_UP TEXT NOT NULL,"
					+ " FOREIGN KEY(SONG_ID) REFERENCES SONGS(ID),"
					+ " FOREIGN KEY(ARTIST_ID) REFERENCES ARTISTS(ID))";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Tables created successfully");
	}

	@Override
	public void createArtistPlayHistoryTables() {
		Connection c = null;
		Statement stmt = null;
		try {
			c = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE artist_day_plays "
					+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " ARTIST_ID INTEGER NOT NULL," + " START_TIME TIMESTAMP NOT NULL,"
					+ " END_TIME TIMESTAMP NOT NULL," + " NUM_PLAYS INT NOT NULL,"
					+ " PERCENT_CHANGE REAL," + " HEATING_UP TEXT NOT NULL,"
					+ " FOREIGN KEY(ARTIST_ID) REFERENCES ARTISTS(ID))";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE artist_week_plays "
					+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " ARTIST_ID INTEGER NOT NULL," + " START_TIME TIMESTAMP NOT NULL,"
					+ " END_TIME TIMESTAMP NOT NULL," + " NUM_PLAYS INT NOT NULL,"
					+ " PERCENT_CHANGE REAL," + " HEATING_UP TEXT NOT NULL,"
					+ " FOREIGN KEY(ARTIST_ID) REFERENCES ARTISTS(ID))";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE artist_month_plays "
					+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " ARTIST_ID INTEGER NOT NULL," + " START_TIME TIMESTAMP NOT NULL,"
					+ " END_TIME TIMESTAMP NOT NULL," + " NUM_PLAYS INT NOT NULL,"
					+ " PERCENT_CHANGE REAL," + " HEATING_UP TEXT NOT NULL,"
					+ " FOREIGN KEY(ARTIST_ID) REFERENCES ARTISTS(ID))";
			stmt.executeUpdate(sql);
			sql = "CREATE TABLE artist_year_plays "
					+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " ARTIST_ID INTEGER NOT NULL," + " START_TIME TIMESTAMP NOT NULL,"
					+ " END_TIME TIMESTAMP NOT NULL," + " NUM_PLAYS INT NOT NULL,"
					+ " PERCENT_CHANGE REAL," + " HEATING_UP TEXT NOT NULL,"
					+ " FOREIGN KEY(ARTIST_ID) REFERENCES ARTISTS(ID))";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Tables created successfully");
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

	@Override
	public void dropArtists() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "DROP TABLE IF EXISTS artists; ";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Artist table dropped successfully");
	}

	@Override
	public void dropSongPlayHistoryTables() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "DROP TABLE IF EXISTS song_day_plays ;"
					+ "DROP TABLE IF EXISTS song_week_plays ;"
					+ "DROP TABLE IF EXISTS song_month_plays ;"
					+ "DROP TABLE IF EXISTS song_year_plays ;";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Song play history tables dropped successfully");
	}

	@Override
	public void dropArtistPlayHistoryTables() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "DROP TABLE IF EXISTS artist_day_plays ;"
					+ "DROP TABLE IF EXISTS artist_week_plays ;"
					+ "DROP TABLE IF EXISTS artist_month_plays ;"
					+ "DROP TABLE IF EXISTS artist_year_plays ;";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Artist play history tables dropped successfully");
	}

}
