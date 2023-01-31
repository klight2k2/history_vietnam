package model;

public class Era extends Historical {
	protected String fromYear;
	protected String toYear;
	protected Era isPreceededBy;
	protected Era isSuccessedBy;
	public Era(String name,String fromYear,String toYear,Era isPrecededBy,Era isSuccessedBy) {
		super(name);
		this.fromYear=fromYear;
		this.toYear=toYear;
		this.isPreceededBy=isPrecededBy;
		this.isSuccessedBy=isSuccessedBy;
	}
	public Era(String name,String fromYear,String toYear) {
		super(name);
		this.fromYear=fromYear;
		this.toYear=toYear;
	
	}
}
