package wroblicky.andrew.euterpe.provider;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class MusicLibrary {

	private Set<String> artistNames;
	private Map<SongIdentificationKey,Integer> playCountLookup;
	private Map<SongIdentificationKey, InputSong> songLookup;
	private final List<InputSong> songs;
	
	public MusicLibrary(List<InputSong> songs) {
		this.songs = songs;
	}

	public List<InputSong> getSongs() {
		return songs;
	}
}
