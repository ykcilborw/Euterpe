package wroblicky.andrew.euterpe.artists;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import wroblicky.andrew.euterpe.dao.SqliteDAOFactory;

public class ArtistDAOImpl implements ArtistDAO {
	

	@Override
	public void createArtistTable() {
		String sql = "CREATE TABLE artists "
				+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ " NAME           TEXT    NOT NULL)";
		try (Connection connection = SqliteDAOFactory.createConnection()) {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(sql)) {
				preparedStatement.executeUpdate(sql);
			}
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Artists table created successfully");
	}
	
	@Override
	public void insertArtist(String artist) {
		String sql = "INSERT INTO ARTISTS (NAME) " + "VALUES ('?');";
		try (Connection connection = SqliteDAOFactory.createConnection()) {
			try (PreparedStatement preparedStatement = connection
					.prepareStatement(sql)) {
				preparedStatement.setString(1, artist);
				preparedStatement.executeUpdate(sql);
			}
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Artist record created successfully");
	}

	@Override
	public Set<Artist> getArtists() {
		Statement stmt = null;
		Set<Artist> artists = new HashSet<>();
		try (Connection connection = SqliteDAOFactory.createConnection()) {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM ARTISTS;");

			while (rs.next()) {
				int artistId = rs.getInt("id");
				String artistName = rs.getString("name");
				artists.add(new Artist(artistId, artistName));
			}
			rs.close();
			stmt.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
		return artists;
	}

	@Override
	public Artist getArtist(String name) {
	    Statement stmt = null;
	    Artist artist = null;
	    try (Connection connection = SqliteDAOFactory.createConnection()) {

	      stmt = connection.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM ARTISTS where name = " + name + ";" );

	      while ( rs.next() ) {
	         int artistId = rs.getInt("artists.id");
	         String artistName  = rs.getString("artists.name");
	         artist = new Artist(artistId, artistName);
	         break;
	      }
	      rs.close();
	      stmt.close();
	      connection.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Operation done successfully");
	    return artist;
	}

	@Override
	public void dropArtists() {
		Statement stmt = null;
		try (Connection connection = SqliteDAOFactory.createConnection()) {
			stmt = connection.createStatement();
			String sql = "DROP TABLE IF EXISTS artists; ";
			stmt.executeUpdate(sql);
			stmt.close();
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Artist table dropped successfully");
	}

}
