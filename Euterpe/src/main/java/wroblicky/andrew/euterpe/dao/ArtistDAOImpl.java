package wroblicky.andrew.euterpe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import wroblicky.andrew.euterpe.Artist;
import wroblicky.andrew.euterpe.PlayHistory;
import wroblicky.andrew.euterpe.TimeInterval;
import wroblicky.andrew.euterpe.TimeScope;

public class ArtistDAOImpl implements ArtistDAO {
	
	private String databaseName;

	@Override
	public List<PlayHistory> getArtistRankings(TimeInterval timeInterval,
			TimeScope timeScope) {
		return null;
	}

	@Override
	public List<PlayHistory> getArtistRankings(TimeInterval timeInterval,
			TimeScope timeScope, int start, int end) {
		return null;
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM ARTISTS;");

			while (rs.next()) {
				int artistId = rs.getInt("id");
				String artistName = rs.getString("name");
				artists.add(new Artist(artistId, artistName));
			}
			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
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

}
