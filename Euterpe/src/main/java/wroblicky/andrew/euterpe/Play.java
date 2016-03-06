package wroblicky.andrew.euterpe;

public final class Play {
	
	private final Song song;
	private final long timestamp;
	
	public Play(Song song, long timestamp) {
		this.song = song;
		this.timestamp = timestamp;
	}
	
	public Song getSong() {
		return song;
	}
	
	public long getTimestamp() {
		return timestamp;
	}
}
