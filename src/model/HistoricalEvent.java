package model;

public class HistoricalEvent extends Historical {
	protected String startDate;
	protected String endDate;
	protected String desc;

	public HistoricalEvent(String name, String startDate, String endDate) {
		super(name);
		this.startDate = startDate;
		this.endDate = endDate;
		// TODO Auto-generated constructor stub
	}

}
