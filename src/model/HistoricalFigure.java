package model;

public class HistoricalFigure extends Historical {
	protected int id;
	public static int idCounter = 1;
	protected String born;
	protected String died;
	protected int livedInEras;
	protected int relatedEvent;
	protected String bornIn;
	protected String desc;
	protected String imageLink;
	
	// constructor test
	public HistoricalFigure(String name, String born, String died) {
		super(name);
		this.id = idCounter++;
		this.died = died;
		this.born = born;
	}

	public HistoricalFigure(String name, String died, String born, String bornIn, String desc) {
		super(name);
		this.id = idCounter++;
		this.died = died;
		this.born = born;
		this.bornIn = bornIn;
		this.desc = desc;
	}

	public HistoricalFigure(String name, String died, String born, String bornIn, String desc, String imageLink) {
		super(name);
		this.died = died;
		this.born = born;
		this.bornIn = bornIn;
		this.desc = desc;
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
		HistoricalFigure.idCounter = idCounter;
	}

	public String getBorn() {
		return born;
	}

	public void setBorn(String born) {
		this.born = born;
	}

	public String getDied() {
		return died;
	}

	public void setDied(String died) {
		this.died = died;
	}

	public int getLivedInEras() {
		return livedInEras;
	}

	public void setLivedInEras(int livedInEras) {
		this.livedInEras = livedInEras;
	}

	public int getRelatedEvent() {
		return relatedEvent;
	}

	public void setRelatedEvent(int relatedEvent) {
		this.relatedEvent = relatedEvent;
	}

	public String getBornIn() {
		return bornIn;
	}

	public void setBornIn(String bornIn) {
		this.bornIn = bornIn;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getImageLink() {
		return imageLink;
	}

	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	
	

}
