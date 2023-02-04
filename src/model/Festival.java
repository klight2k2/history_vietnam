package model;

public class Festival extends Historical {
	protected int id;
	public static int idCounter = 1;
	protected String location;
	protected String doiTuongSuyTon;
	protected String desc;
	protected String holdTime;

	public Festival(String name, String holdTime, String location, String doiTuongSuyTon, String desc) {
		super(name);
		this.id = idCounter++;
		this.holdTime = holdTime;
		this.location = location;
		this.desc = desc;
		this.doiTuongSuyTon = doiTuongSuyTon;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		Festival.idCounter = idCounter;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDoiTuongSuyTon() {
		return doiTuongSuyTon;
	}

	public void setDoiTuongSuyTon(String doiTuongSuyTon) {
		this.doiTuongSuyTon = doiTuongSuyTon;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getHoldTime() {
		return holdTime;
	}

	public void setHoldTime(String holdTime) {
		this.holdTime = holdTime;
	}

	
}
