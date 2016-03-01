package wroblicky.andrew.euterpe.dao;

import java.util.List;

import wroblicky.andrew.euterpe.Play;

public interface PlayDAO {
	
	public abstract List<Play> getPlays();
	
	public abstract void insertPlay(Play play);
	
	public abstract void createPlayTable();

	public abstract void dropPlays();

}
