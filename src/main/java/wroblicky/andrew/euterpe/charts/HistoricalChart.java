package wroblicky.andrew.euterpe.charts;

import java.util.List;

public class HistoricalChart {
	
	private final TimeInterval timeInterval;
	private final ChartCategory chartCategory;
	private final List<ChartEntry> results;
	
	public HistoricalChart(TimeInterval timeInterval, ChartCategory chartCategory, List<ChartEntry> results) {
		this.timeInterval = timeInterval;
		this.chartCategory = chartCategory;
		this.results = results;
	}
	
	public TimeInterval getTimeInterval() {
		return timeInterval;
	}

	public ChartCategory getChartCategory() {
		return chartCategory;
	}

	public List<ChartEntry> getResults() {
		return results;
	}
}
