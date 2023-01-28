package history;

public class Festival extends Historical {
	protected String localDate;
	protected String location;
	public Festival(String name, String localDate,String location) {
		super(name);
		this.localDate=localDate;
		this.location=location;
	}

}
