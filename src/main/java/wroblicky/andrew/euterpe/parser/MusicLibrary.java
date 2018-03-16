package wroblicky.andrew.euterpe.parser;

import java.util.Map;
import java.util.Set;

public class MusicLibrary {

	private Set<String> artistNames;  // do new artists need to be inserted
	private Map<String, InputSong> persistentIDToSong; // do new songs need to be inserted
	private Map<String,Integer> persistentIDToPlayCount; // do new song plays need to be added
	
	public MusicLibrary(Set<String> artistNames,Map<String, InputSong> persistentIDToSong, Map<String,Integer> playCountLookup) {
		this.artistNames = artistNames;
		this.persistentIDToSong = persistentIDToSong;
		this.persistentIDToPlayCount = playCountLookup;
	}
	
	public Set<String> getArtistNames() {
		return artistNames;
	}


	public Map<String, InputSong> getSongLookup() {
		return persistentIDToSong;
	}


	public Map<String, Integer> getPlayCountLookup() {
		return persistentIDToPlayCount;
	}
}
