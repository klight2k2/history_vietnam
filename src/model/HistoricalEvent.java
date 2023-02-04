package model;

public class HistoricalEvent extends Historical {
	protected int id;
	public static int idCounter = 1;
	protected String startDate;
	protected String endDate;
	protected String desc;
	protected int relatedEraId = 0;

	public HistoricalEvent(String name, String startDate, String endDate) {
		super(name);
		this.id = idCounter++;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public HistoricalEvent(String name, String startDate, String endDate, int relatedEraId) {
		super(name);
		this.id = idCounter++;
		this.startDate = startDate;
		this.endDate = endDate;
		this.relatedEraId = relatedEraId;
	}

	public int getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public String getStartDate() {
		// TODO Auto-generated method stub
		return this.startDate;
	}

	public String getEndDate() {
		// TODO Auto-generated method stub
		return this.endDate;
	}

	public void setEra(int eraId) {
		this.relatedEraId = eraId;
	}
	
	
}
