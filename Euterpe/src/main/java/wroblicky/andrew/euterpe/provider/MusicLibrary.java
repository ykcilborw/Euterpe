package wroblicky.andrew.euterpe.provider;

import java.util.List;

import wroblicky.andrew.euterpe.InputSong;

public class MusicLibrary {

	private final List<InputSong> songs;
	
	public MusicLibrary(List<InputSong> songs) {
		this.songs = songs;
	}

	public List<InputSong> getSongs() {
		return songs;
	}
}
