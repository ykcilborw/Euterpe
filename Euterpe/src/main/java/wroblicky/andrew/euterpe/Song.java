package wroblicky.andrew.euterpe;


public class Song {
	
	private long id;
	private String name;
	private Artist artist;
    private long dateAdded;
    
    public Song(String name, Artist artist, long dateAdded) {
    	this.name = name;
    	this.artist = artist;
    	this.dateAdded = dateAdded;
    }
    
    public Song(long id, String name, Artist artist, long dateAdded) {
    	this.id = id;
    	this.name = name;
    	this.artist = artist;
    	this.dateAdded = dateAdded;
    }
    
	public long getID() {
		return id;
	}
	
	public void setID(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Artist getArtist() {
		return artist;
	}
	
	public void setArtist(Artist artist) {
		this.artist = artist;
	}
	
	public long getDateAdded() {
		return dateAdded;
	}
	
	public void setDateAdded(long dateAdded) {
		this.dateAdded = dateAdded;
	}
}
