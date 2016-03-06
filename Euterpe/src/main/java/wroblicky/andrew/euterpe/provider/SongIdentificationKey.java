package wroblicky.andrew.euterpe.provider;

public class SongIdentificationKey {

	private final String artist;
	private final String songName;
	private final long dateAdded;

	public SongIdentificationKey(String artist, String songName, long dateAdded) {
		this.artist = artist;
		this.songName = songName;
		this.dateAdded = dateAdded;
	}

	public String getArtist() {
		return artist;
	}

	public String getSongName() {
		return songName;
	}

	public long getDateAdded() {
		return dateAdded;
	}
}
