package wroblicky.andrew.euterpe.dao;

import java.util.List;

import wroblicky.andrew.euterpe.Play;

public interface PlayDAO {
	
	void createPlayTable();
	
	void insertPlay(Play play);
	
	List<Play> getPlays();
	
	void dropPlays();

}
