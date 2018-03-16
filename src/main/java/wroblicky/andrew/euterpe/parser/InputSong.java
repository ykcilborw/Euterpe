package wroblicky.andrew.euterpe.parser;


public class InputSong {
	
	private String name;
	private String artist;
	private String genre;
    private long dateAdded;
    private int numPlays;
    private long mostRecentPlayDate;
    private String persistentID;
    
    public InputSong(String name, String artist, String genre, long dateAdded, int numPlays, long mostRecentPlayDate, String persistentID) {
    	this.name = name;
    	this.artist = artist;
    	this.dateAdded = dateAdded;
    	this.numPlays = numPlays;
    	this.genre = genre;
    	this.mostRecentPlayDate = mostRecentPlayDate;
    	this.persistentID = persistentID;
    }
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public long getDateAdded() {
		return dateAdded;
	}
	
	public void setDateAdded(long dateAdded) {
		this.dateAdded = dateAdded;
	}
	
	public int getNumPlays() {
		return numPlays;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public long getMostRecentPlayDate() {
		return mostRecentPlayDate;
	}
	
	public String getPersistentID() {
		return persistentID;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
		result = prime * result + (int) (dateAdded ^ (dateAdded >>> 32));
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result
				+ (int) (mostRecentPlayDate ^ (mostRecentPlayDate >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + numPlays;
		result = prime * result
				+ ((persistentID == null) ? 0 : persistentID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InputSong other = (InputSong) obj;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		if (dateAdded != other.dateAdded)
			return false;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (mostRecentPlayDate != other.mostRecentPlayDate)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (numPlays != other.numPlays)
			return false;
		if (persistentID == null) {
			if (other.persistentID != null)
				return false;
		} else if (!persistentID.equals(other.persistentID))
			return false;
		return true;
	}
}
