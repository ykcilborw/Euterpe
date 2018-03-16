package wroblicky.andrew.euterpe.charts;

public interface HistoricalChartDAO {
	
	void createHistoricalChartTable(TimeInterval timeInterval, TimeScope timeScope, ChartCategory chartCategory);
	
	void insertHistoricalChart(HistoricalChart historicalChart);
	
	HistoricalChart getHistoricalChart(TimeInterval timeInterval, TimeScope timeScope, ChartCategory chartCategory);
	
	void deleteHistoricalChart(TimeInterval timeInterval, TimeScope timeScope, ChartCategory chartCategory);
	
	void dropHistoricalCharts();

}
