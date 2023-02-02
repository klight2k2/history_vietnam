package model;

public class HistoricalEvent extends Historical {
	protected int id;
	public static int idCounter = 1;
	protected String startDate;
	protected String endDate;
	protected String desc;

	public HistoricalEvent(String name, String startDate, String endDate) {
		super(name);
		this.id = idCounter++;
		this.startDate = startDate;
		this.endDate = endDate;
		// TODO Auto-generated constructor stub
	}

}
