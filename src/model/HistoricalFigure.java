package model;

import java.util.ArrayList;
import java.util.List;

public class HistoricalFigure extends Historical {
	protected int id;
	public static int idCounter = 1;
	protected String born;
	protected String died;
	protected List<Integer> relatedEraId = new ArrayList<>();
	protected List<Integer> relatedEventId = new ArrayList<>();
	protected String bornIn;
	protected String desc;
	protected String imageLink;

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
		this.id = idCounter++;
		this.died = died;
		this.born = born;
		this.bornIn = bornIn;
		this.desc = desc;
		this.imageLink = imageLink;
	}

	public String getBorn() {
		return this.born;
	}

	public String getDied() {
		return this.died;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Integer> getRelatedEraId() {
		return relatedEraId;
	}

	public void addRelatedEraId(int relatedEraId) {
		this.relatedEraId.add(relatedEraId);
	}

	public List<Integer> getRelatedEventId() {
		return relatedEventId;
	}

	public void addRelatedEventId(int relatedEventId) {
		this.relatedEventId.add(relatedEventId);
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

	public static int getIdCounter() {
		return idCounter;
	}

	public static void setIdCounter(int idCounter) {
		HistoricalFigure.idCounter = idCounter;
	}

	public void setBorn(String born) {
		this.born = born;
	}

	public void setDied(String died) {
		this.died = died;
	}

	public void setRelatedEraId(List<Integer> relatedEraId) {
		this.relatedEraId = relatedEraId;
	}

	public void setRelatedEventId(List<Integer> relatedEventId) {
		this.relatedEventId = relatedEventId;
	}

	
//	public void setRelated(ArrayList<Integer> eraId, ArrayList<Integer> eventId) {
//		this.relatedEraId = eraId;
//		this.relatedEventId = eventId;
//	}

}
