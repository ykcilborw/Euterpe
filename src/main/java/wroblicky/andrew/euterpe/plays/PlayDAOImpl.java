package wroblicky.andrew.euterpe.plays;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import wroblicky.andrew.euterpe.artists.Artist;
import wroblicky.andrew.euterpe.dao.SqliteDAOFactory;
import wroblicky.andrew.euterpe.songs.Song;

public class PlayDAOImpl implements PlayDAO {
	
	
	@Override
	public void createPlayTable() {
		Statement stmt = null;
		try (Connection connection = SqliteDAOFactory.createConnection()) {

			stmt = connection.createStatement();
			String sql = "CREATE TABLE plays "
					+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ " SONG_ID INTEGER NOT NULL,"
					+ " TIMESTAMP TIMESTAMP NOT NULL,"
					+ " FOREIGN KEY(SONG_ID) REFERENCES SONGS(ID))";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Plays table created successfully");
	}
	
	@Override
	public void insertPlay(Play play) {
		Statement stmt = null;
		try (Connection connection = SqliteDAOFactory.createConnection()) {

			stmt = connection.createStatement();
			String sql = "INSERT INTO PLAYS (SONG_ID,TIMESTAMP) " + "VALUES ("
					+ play.getSong().getID() + "," + play.getTimestamp() + ");";
			stmt.executeUpdate(sql);

			stmt.close();
			connection.commit();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Play record created successfully");
	}

	@Override
	public List<Play> getPlays() {
	    Statement stmt = null;
	    List<Play> plays = new ArrayList<Play>();
	    try (Connection connection = SqliteDAOFactory.createConnection()) {

	      stmt = connection.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM PLAYS JOIN SONGS, ARTISTS WHERE songs.id = plays.song_id and artists.id = plays.artist_id ;" );

	      while ( rs.next() ) {
	         int id = rs.getInt("songs.id");
	         String songName = rs.getString("songs.name");
	         int artistId = rs.getInt("artists.id");
	         String artistName  = rs.getString("artists.name");
	         Artist artist = new Artist(artistId, artistName);
	         Long dateAdded = rs.getLong("date_added");
	         Song song = new Song(id, songName, artist, dateAdded);
	         plays.add(new Play(song, rs.getLong("plays.timestamp")));
	      }
	      rs.close();
	      stmt.close();
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Plays retrieved  successfully");
	    return plays;
	}
	
	@Override
	public Set<Play> getPlays(Artist artist) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Play> getPlays(Song song) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dropPlays() {
		Statement stmt = null;
		try (Connection connection = SqliteDAOFactory.createConnection()) {

			stmt = connection.createStatement();
			String sql = "DROP TABLE IF EXISTS plays; ";
			stmt.executeUpdate(sql);
			stmt.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Play table dropped successfully");
	}
}
