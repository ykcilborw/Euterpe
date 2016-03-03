package wroblicky.andrew.euterpe;

import java.util.ArrayList;
import java.util.List;

import wroblicky.andrew.euterpe.dao.ArtistDAO;
import wroblicky.andrew.euterpe.dao.DAOFactory;
import wroblicky.andrew.euterpe.dao.PlayDAO;
import wroblicky.andrew.euterpe.dao.SongDAO;
import wroblicky.andrew.euterpe.provider.DataLoader;

public class SqlTestManager {
	
	private ArtistDAO artistDAO;
	private SongDAO songDAO;
	private PlayDAO playDAO;
	private DataLoader dataProvider;
	
	
	public SqlTestManager(DAOFactory daoFactory, DataLoader dataProvider) {
		this.artistDAO = daoFactory.getArtistDAO();
		this.songDAO = daoFactory.getSongDAO();
		this.playDAO = daoFactory.getPlayDAO();
		this.dataProvider = dataProvider;
	}
	
	public void prepareDatabase() {
		//databaseManager.dropSongPlayHistoryTables();
		//databaseManager.dropArtistPlayHistoryTables();
		playDAO.dropPlays();
		songDAO.dropSongs();
		artistDAO.dropArtists();
		artistDAO.createArtistTable();
		songDAO.createSongTable();
		playDAO.createPlayTable();
		//databaseManager.createArtistPlayHistoryTables();
		//databaseManager.createSongPlayHistoryTables();
		insertArtists();
		insertSongs();
		insertPlays();
		//insertSongPlayHistories();
		//insertArtistPlayHistories();
		dataProvider.fetchAndInsertLatestData();
	}
	
	public void insertArtists() {
		List<String> artists = new ArrayList<>();
		artists.add("Muse");
		artists.add("AFI");
		artists.add("Flyleaf");
		artists.add("Kaskade");
		artists.add("Leftfield");
		artists.add("Blur");
		artists.add("Elastica");
		artists.add("Echobelly");
		for (String artist : artists) {
			artistDAO.insertArtist(artist);
		}
	}
	
	public void insertSongs() {
		List<Song> songs = new ArrayList<Song>();
		songs.add(new Song("Time Is Running Out", new Artist("Muse"), 1440990716));
		songs.add(new Song("Knights of Cydonia", new Artist("Muse"), 1440980216));
		songs.add(new Song("Uprising", new Artist("Muse"), 1440944444));
		songs.add(new Song("Hysteria", new Artist("Muse"), 1440933333));
		songs.add(new Song("Miss Murder", new Artist("AFI"), 1440980216));
		songs.add(new Song("Silver and Cold", new Artist("AFI"), 1440980216));
		songs.add(new Song("Medicate", new Artist("AFI"), 1440980216));
		songs.add(new Song("All Around Me", new Artist("Flyleaf"), 1440980216));
		songs.add(new Song("Something To Give", new Artist("Kaskade"), 1440980216));
		songs.add(new Song("Moonbaton", new Artist("Leftfield"), 1440980216));
		songs.add(new Song("Song 2", new Artist("Blur"), 1440980216));
		songs.add(new Song("The Universal", new Artist("Blur"), 1440980216));
		songs.add(new Song("Coffee and TV", new Artist("Blur"), 1440980216));
		songs.add(new Song("Never There", new Artist("Elastica"), 1440980216));
		songs.add(new Song("S.O.F.T.", new Artist("Elastica"), 1440980216));
		songs.add(new Song("Dark Therapy", new Artist("Echobelly"), 1440980216));
		for (Song song : songs) {
			songDAO.insertSong(song);
		}
	}
	
	public void insertPlays() {
		List<Play> plays = new ArrayList<Play>();
		plays.add(new Play(new Song(1, "Time Is Running Out", new Artist("Muse"), 1440990716), new Artist("Muse"), 1440990716));
		plays.add(new Play(new Song(1, "Time Is Running Out", new Artist("Muse"), 1440990716), new Artist("Muse"), 1440990717));
		plays.add(new Play(new Song(1, "Time Is Running Out", new Artist("Muse"), 1440990716), new Artist("Muse"), 1440990718));
		plays.add(new Play(new Song(1, "Time Is Running Out", new Artist("Muse"), 1440990716), new Artist("Muse"), 1440990719));
		plays.add(new Play(new Song(1, "Time Is Running Out", new Artist("Muse"), 1440990716), new Artist("Muse"), 1440990720));
		plays.add(new Play(new Song(1, "Time Is Running Out", new Artist("Muse"), 1440990716), new Artist("Muse"), 1440990721));
		plays.add(new Play(new Song(1, "Time Is Running Out", new Artist("Muse"), 1440990716), new Artist("Muse"), 1440990722));
		plays.add(new Play(new Song(1, "Time Is Running Out", new Artist("Muse"), 1440990716), new Artist("Muse"), 1440990723));
		plays.add(new Play(new Song(1, "Knights of Cydonia", new Artist("Muse"), 1440990716), new Artist("Muse"), 1440990716));
		plays.add(new Play(new Song(1, "Knights of Cydonia", new Artist("Muse"), 1440990716), new Artist("Muse"), 1440990716));
		plays.add(new Play(new Song(1, "Knights of Cydonia", new Artist("Muse"), 1440990716), new Artist("Muse"), 1440990716));
		plays.add(new Play(new Song(1, "Knights of Cydonia", new Artist("Muse"), 1440990716), new Artist("Muse"), 1440990716));
		plays.add(new Play(new Song(1, "Uprising", new Artist("Muse"), 1440990716), new Artist("Muse"), 1440990716));
		plays.add(new Play(new Song(1, "Uprising", new Artist("Muse"), 1440990716), new Artist("Muse"), 1440990716));
		plays.add(new Play(new Song(1, "Hysteria", new Artist("Muse"), 1440990716), new Artist("Muse"), 1440990716));
		plays.add(new Play(new Song(1, "Miss Murder", new Artist("AFI"), 1440990716), new Artist("AFI"), 1440990716));
		plays.add(new Play(new Song(1, "Miss Murder", new Artist("AFI"), 1440990716), new Artist("AFI"), 1440990716));
		plays.add(new Play(new Song(1, "Silver and Cold", new Artist("AFI"), 1440990716), new Artist("AFI"), 1440990716));
		plays.add(new Play(new Song(1, "Medicate", new Artist("AFI"), 1440990716), new Artist("AFI"), 1440990716));
		plays.add(new Play(new Song(1, "All Around Me", new Artist("Flyleaf"), 1440990716), new Artist("Flyleaf"), 1440990716));
		plays.add(new Play(new Song(1, "All Around Me", new Artist("Flyleaf"), 1440990716), new Artist("Flyleaf"), 1440990716));
		plays.add(new Play(new Song(1, "All Around Me", new Artist("Flyleaf"), 1440990716), new Artist("Flyleaf"), 1440990716));
		plays.add(new Play(new Song(1, "All Around Me", new Artist("Flyleaf"), 1440990716), new Artist("Flyleaf"), 1440990716));
		plays.add(new Play(new Song(1, "All Around Me", new Artist("Flyleaf"), 1440990716), new Artist("Flyleaf"), 1440990716));
		plays.add(new Play(new Song(1, "All Around Me", new Artist("Flyleaf"), 1440990716), new Artist("Flyleaf"), 1440990716));
		plays.add(new Play(new Song(1, "All Around Me", new Artist("Flyleaf"), 1440990716), new Artist("Flyleaf"), 1440990716));
		plays.add(new Play(new Song(1, "All Around Me", new Artist("Flyleaf"), 1440990716), new Artist("Flyleaf"), 1440990716));
		plays.add(new Play(new Song(1, "All Around Me", new Artist("Flyleaf"), 1440990716), new Artist("Flyleaf"), 1440990716));
		plays.add(new Play(new Song(1, "All Around Me", new Artist("Flyleaf"), 1440990716), new Artist("Flyleaf"), 1440990716));
		plays.add(new Play(new Song(1, "All Around Me", new Artist("Flyleaf"), 1440990716), new Artist("Flyleaf"), 1440990716));
	}
	
	public void insertSongPlayHistories() {
		
	}
	
	public void insertArtistPlayHistories() {
		
	}
}
