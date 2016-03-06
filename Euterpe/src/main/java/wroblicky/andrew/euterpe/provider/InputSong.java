package wroblicky.andrew.euterpe.provider;


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
}
