package wroblicky.andrew.euterpe.songs;

import java.util.Set;

import wroblicky.andrew.euterpe.artists.Artist;
import wroblicky.andrew.euterpe.provider.SongIdentificationKey;

public interface SongDAO {
	
	void createSongTable();
	
	void insertSong(Song song);
	
	Set<Song> getSongs();
	
	Song getSong(SongIdentificationKey key);
	
	Song getSong(Artist artist, String name);
	
	Song getSong(String id);
	
	void dropSongs();

}
