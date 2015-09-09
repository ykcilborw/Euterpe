package wroblicky.andrew.euterpe;

import java.util.ArrayList;
import java.util.List;

public class TestManager {
	
	private DatabaseManager databaseManager;
	private DataProvider dataProvider;
	
	
	public TestManager(DatabaseManager databaseManager, DataProvider dataProvider) {
		this.databaseManager = databaseManager;
		this.dataProvider = dataProvider;
	}
	
	public void prepareDatabase() {
		databaseManager.dropSongPlayHistoryTables();
		databaseManager.dropArtistPlayHistoryTables();
		databaseManager.dropPlays();
		databaseManager.dropSongs();
		databaseManager.dropArtists();
		databaseManager.createArtistTable();
		databaseManager.createSongTable();
		databaseManager.createPlayTable();
		databaseManager.createArtistPlayHistoryTables();
		databaseManager.createSongPlayHistoryTables();
		insertArtists();
		insertSongs();
		insertPlays();
		insertSongPlayHistories();
		insertArtistPlayHistories();
		dataProvider.fetchAndInsertLatestData();
	}
	
	public void insertArtists() {
		List<Artist> artists = new ArrayList<Artist>();
		artists.add(new Artist("Muse"));
		artists.add(new Artist("AFI"));
		artists.add(new Artist("Flyleaf"));
		artists.add(new Artist("Kaskade"));
		artists.add(new Artist("Leftfield"));
		artists.add(new Artist("Blur"));
		artists.add(new Artist("Elastica"));
		artists.add(new Artist("Echobelly"));
		for (Artist artist : artists) {
			databaseManager.insertArtist(artist);
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
			databaseManager.insertSong(song);
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
