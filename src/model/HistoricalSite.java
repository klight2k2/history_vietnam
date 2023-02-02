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
		this.builtIn = builtIn;
		this.location = location;
		this.objectWorship = objectWorship;
		this.loaiHinhXepHang = loaiHinhXepHang;
		this.loaiXepHang = loaiXepHang;
		this.imageLink = imageLink;
	}

}
