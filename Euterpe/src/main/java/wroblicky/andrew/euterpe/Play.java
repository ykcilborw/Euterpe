package wroblicky.andrew.euterpe;

public final class Play {
	
	private final Song song;
	private final Artist artist;
	private final long timestamp;
	
	public Play(Song song, Artist artist, long timestamp) {
		this.song = song;
		this.artist = artist;
		this.timestamp = timestamp;
	}
	
	public Song getSong() {
		return song;
	}
	
	public Artist getArtist() {
		return artist;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
}
