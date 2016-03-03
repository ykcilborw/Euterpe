package wroblicky.andrew.euterpe.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqliteDAOFactory extends DAOFactory {
	
	public static final String DRIVER= "org.sqlite.JDBC";
  
	public static final String DBNAME= "test.db";

	public ArtistDAO getArtistDAO() {
		return new ArtistDAOImpl();
	}	
	
	public SongDAO getSongDAO() {
		return new SongDAOImpl();
	}
	
	public PlayDAO getPlayDAO() {
		return new PlayDAOImpl();
	}
	
	public HistoricalChartDAO getHistoricalChartDAO() {
		return new HistoricalChartDAOImpl();
	}
	
	public static Connection createConnection() {
		Connection connection = null;
	    try {
		      Class.forName(DRIVER);
		      connection = DriverManager.getConnection("jdbc:sqlite:" + DBNAME);
		      connection.setAutoCommit(false);
		      System.out.println("Opened database successfully");
	    } catch (Exception e) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		}
	    return connection;
	}
}
