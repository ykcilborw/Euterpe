package wroblicky.andrew.euterpe.provider;

import java.util.Set;

import wroblicky.andrew.euterpe.artists.Artist;
import wroblicky.andrew.euterpe.plays.Play;
import wroblicky.andrew.euterpe.songs.Song;

public final class UpdateSet {
	
	private final Set<Play> plays;
	private final Set<Song> songs;
	private final Set<Artist> artists;
	
	public UpdateSet(Set<Play> plays, Set<Song> songs, Set<Artist> artists) {
		this.plays = plays;
		this.songs = songs;
		this.artists = artists;
	}
	
	public Set<Play> getPlays() {
		return plays;
	}
	
	public Set<Song> getSongs() {
		return songs;
	}
	
	public Set<Artist> getArtists() {
		return artists;
	}
}
