package wroblicky.andrew.euterpe.dao;

import wroblicky.andrew.euterpe.artists.ArtistDAO;
import wroblicky.andrew.euterpe.charts.HistoricalChartDAO;
import wroblicky.andrew.euterpe.plays.PlayDAO;
import wroblicky.andrew.euterpe.songs.SongDAO;

public abstract class DAOFactory {

	public abstract ArtistDAO getArtistDAO();

	public abstract SongDAO getSongDAO();

	public abstract PlayDAO getPlayDAO();
	
	public abstract HistoricalChartDAO getHistoricalChartDAO();

	public static DAOFactory getDAOFactory(DatabaseType whichFactory) {
		switch (whichFactory) {
		case SQLITE:
			return new SqliteDAOFactory();
		default:
			return null;
		}
	}

}
