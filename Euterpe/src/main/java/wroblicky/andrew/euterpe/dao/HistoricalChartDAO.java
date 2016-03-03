package wroblicky.andrew.euterpe.dao;

import wroblicky.andrew.euterpe.ChartCategory;
import wroblicky.andrew.euterpe.HistoricalChart;
import wroblicky.andrew.euterpe.TimeInterval;
import wroblicky.andrew.euterpe.TimeScope;

public interface HistoricalChartDAO {
	
	public void createHistoricalChartTable(TimeInterval timeInterval, TimeScope timeScope, ChartCategory chartCategory);
	
	public void insertHistoricalChart(HistoricalChart historicalChart);
	
	public HistoricalChart getHistoricalChart(TimeInterval timeInterval, TimeScope timeScope, ChartCategory chartCategory);
	
	public void deleteHistoricalChart(TimeInterval timeInterval, TimeScope timeScope, ChartCategory chartCategory);
	
	public void dropHistoricalCharts();

}
