package wroblicky.andrew.euterpe.charts;

import wroblicky.andrew.euterpe.charts.ChartCategory;

public interface ChartEntry {
	
	ChartCategory getChartCategory();
	
	String getID();

	int getNumPlays();
	
}
