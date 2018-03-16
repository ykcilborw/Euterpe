package wroblicky.andrew.euterpe.artists;

public class Artist {
	
	private long id;
	private String name;
	
	public Artist(String name) {
		this.name = name;
	}
	
	public Artist(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public long getID() {
		return id;
	}
	
	public void setID(long ID) {
		this.id = ID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
