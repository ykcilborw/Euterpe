package wroblicky.andrew.euterpe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import wroblicky.andrew.euterpe.PlayHistory;

public class DatabaseManagerImpl implements DatabaseManager {
	
	private String databaseName;
	
	@Override
	public void insertPlayHistory(PlayHistory playHistory) {
		// TODO Auto-generated method stub
	}

	@Override
	public void updatePlayHistory(PlayHistory playHistory) {
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
