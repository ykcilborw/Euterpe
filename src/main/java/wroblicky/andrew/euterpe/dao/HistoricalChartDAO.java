package wroblicky.andrew.euterpe.dao;

import wroblicky.andrew.euterpe.ChartCategory;
import wroblicky.andrew.euterpe.HistoricalChart;
import wroblicky.andrew.euterpe.TimeInterval;
import wroblicky.andrew.euterpe.TimeScope;

public interface HistoricalChartDAO {
	
	void createHistoricalChartTable(TimeInterval timeInterval, TimeScope timeScope, ChartCategory chartCategory);
	
	void insertHistoricalChart(HistoricalChart historicalChart);
	
	HistoricalChart getHistoricalChart(TimeInterval timeInterval, TimeScope timeScope, ChartCategory chartCategory);
	
	void deleteHistoricalChart(TimeInterval timeInterval, TimeScope timeScope, ChartCategory chartCategory);
	
	void dropHistoricalCharts();

}
