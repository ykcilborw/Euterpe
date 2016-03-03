package wroblicky.andrew.euterpe.provider;

import java.util.Map;
import java.util.Set;

public class MusicLibrary {

	private Set<String> artistNames;  // do new artists need to be inserted
	private Map<SongIdentificationKey, InputSong> songLookup; // do new songs need to be inserted
	private Map<SongIdentificationKey,Integer> playCountLookup; // do new song plays need to be added
	
	public MusicLibrary(Set<String> artistNames,Map<SongIdentificationKey, InputSong> songLookup, Map<SongIdentificationKey,Integer> playCountLookup) {
		this.artistNames = artistNames;
		this.songLookup = songLookup;
		this.playCountLookup = playCountLookup;
	}
	
	public Set<String> getArtistNames() {
		return artistNames;
	}


	public Map<SongIdentificationKey, InputSong> getSongLookup() {
		return songLookup;
	}


	public Map<SongIdentificationKey, Integer> getPlayCountLookup() {
		return playCountLookup;
	}
}
