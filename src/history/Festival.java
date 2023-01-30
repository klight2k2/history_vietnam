package history;

public class Festival extends Historical {
	protected String location;
	protected String doiTuongSuyTon;
	protected String desc;
	protected String holdTime;

	public Festival(String name, String holdTime, String location, String doiTuongSuyTon, String desc) {
		super(name);
		this.holdTime = holdTime;
		this.location = location;
		this.desc = desc;
		this.doiTuongSuyTon = doiTuongSuyTon;
	}

}
