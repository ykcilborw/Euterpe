package wroblicky.andrew.euterpe.charts;

public class HistoricalChartDAOImpl implements HistoricalChartDAO {

	@Override
	public void createHistoricalChartTable(TimeInterval timeInterval,
			TimeScope timeScope, ChartCategory chartCategory) {
		// TODO Auto-generated method stub
		// columns: object_id, num_plays, last_interval_plays
		// future projections and heating up calculated in business logic layer
		
	}

	@Override
	public void insertHistoricalChart(HistoricalChart historicalChart) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HistoricalChart getHistoricalChart(TimeInterval timeInterval,
			TimeScope timeScope, ChartCategory chartCategory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteHistoricalChart(TimeInterval timeInterval,
			TimeScope timeScope, ChartCategory chartCategory) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dropHistoricalCharts() {
		// TODO Auto-generated method stub
		
	}

}
