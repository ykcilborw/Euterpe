package wroblicky.andrew.euterpe.plays;

import wroblicky.andrew.euterpe.charts.HeatingUp;
import wroblicky.andrew.euterpe.charts.TimeInterval;

public class PlayHistory {
	
	private TimeInterval timeInterval;
	private String artist;
	private int numPlays;
	private int percentChangeInPlayAmount;
	private HeatingUp heatingUp;
	
	public TimeInterval getTimeInterval() {
		return timeInterval;
	}
	
	public void setTimeInterval(TimeInterval timeInterval) {
		this.timeInterval = timeInterval;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public int getNumPlays() {
		return numPlays;
	}
	
	public void setNumPlays(int numPlays) {
		this.numPlays = numPlays;
	}
	
	public int getPercentChangeInPlayAmount() {
		return percentChangeInPlayAmount;
	}
	
	public void setPercentChangeInPlayAmount(int percentChangeInPlayAmount) {
		this.percentChangeInPlayAmount = percentChangeInPlayAmount;
	}
	
	public HeatingUp getHeatingUp() {
		return heatingUp;
	}
	
	public void setHeatingUp(HeatingUp heatingUp) {
		this.heatingUp = heatingUp;
	}

}
