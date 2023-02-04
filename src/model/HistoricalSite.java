package model;

public class HistoricalSite extends Historical {
	protected int id;
	public static int idCounter = 1;
	protected String location;
	protected String builtIn;
	protected String objectWorship;
	protected String loaiHinhXepHang;
	protected String loaiXepHang;
	protected String imageLink;

	public HistoricalSite(String name, String builtIn, String location, String objectWorship, String loaiXepHang,
			String loaiHinhXepHang) {
		super(name);
		// TODO Auto-generated constructor stub
		this.id = idCounter++;
		this.builtIn = builtIn;
		this.location = location;
		this.objectWorship = objectWorship;
		this.loaiHinhXepHang = loaiHinhXepHang;
		this.loaiXepHang = loaiXepHang;
	}

	public HistoricalSite(String name, String builtIn, String location, String objectWorship, String loaiXepHang,
			String loaiHinhXepHang, String imageLink) {
		super(name);
		// TODO Auto-generated constructor stub
		this.id = idCounter++;
		this.builtIn = builtIn;
		this.location = location;
		this.objectWorship = objectWorship;
		this.loaiHinhXepHang = loaiHinhXepHang;
		this.loaiXepHang = loaiXepHang;
		this.imageLink = imageLink;
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
		HistoricalSite.idCounter = idCounter;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBuiltIn() {
		return builtIn;
	}

	public void setBuiltIn(String builtIn) {
		this.builtIn = builtIn;
	}

	public String getObjectWorship() {
		return objectWorship;
	}

	public void setObjectWorship(String objectWorship) {
		this.objectWorship = objectWorship;
	}

	public String getLoaiHinhXepHang() {
		return loaiHinhXepHang;
	}

	public void setLoaiHinhXepHang(String loaiHinhXepHang) {
		this.loaiHinhXepHang = loaiHinhXepHang;
	}

	public String getLoaiXepHang() {
		return loaiXepHang;
	}

	public void setLoaiXepHang(String loaiXepHang) {
		this.loaiXepHang = loaiXepHang;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	
	
}
