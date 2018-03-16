package wroblicky.andrew.euterpe.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import wroblicky.andrew.euterpe.Artist;
import wroblicky.andrew.euterpe.Song;
import wroblicky.andrew.euterpe.provider.SongIdentificationKey;

public class SongDAOImpl implements SongDAO {
	
	
	public void createSongTable() {
		Statement stmt = null;
		try (Connection connection = SqliteDAOFactory.createConnection()) {

			stmt = connection.createStatement();
			String sql = "CREATE TABLE songs "
					+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " NAME           TEXT    NOT NULL,"
					+ " ARTIST_ID      INTEGER     NOT NULL,"
					+ " DATE_ADDED     TIMESTAMP,"
					+ " FOREIGN KEY(ARTIST_ID) REFERENCES ARTISTS(ID))";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Songs table created successfully");
		
	}
	
	@Override
	public void insertSong(Song song) {
		Statement stmt = null;
		try (Connection connection = SqliteDAOFactory.createConnection()) {
			stmt = connection.createStatement();
			String sql = "INSERT INTO SONGS (NAME,ARTIST_ID,DATE_ADDED) "
					+ "VALUES ('" + song.getName() + "',"
					+ song.getArtist().getID() + "," + song.getDateAdded()
					+ ");";
			stmt.executeUpdate(sql);

			stmt.close();
			connection.commit();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Song record created successfully");
	}


	@Override
	public Set<Song> getSongs() {
	    Statement stmt = null;
	    Set<Song> songs = new HashSet<>();
	    try (Connection connection = SqliteDAOFactory.createConnection()) {
	      stmt = connection.createStatement();
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
	    } catch (Exception e) {
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
	public Song getSong(String id) {
		return null;
	}

	@Override
	public void dropSongs() {
		Statement stmt = null;
		try (Connection connection = SqliteDAOFactory.createConnection()) {
			stmt = connection.createStatement();
			String sql = "DROP TABLE IF EXISTS songs; ";
			stmt.executeUpdate(sql);
			stmt.close();
			connection.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Song table dropped successfully");
	}

	@Override
	public Song getSong(SongIdentificationKey key) {
		// TODO Auto-generated method stub
		return null;
	}

}
