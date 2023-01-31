package model;

public class HistoricalFigure extends Historical {
	protected String born;
	protected String died;
	protected Era livedInEras;
	protected HistoricalEvent relatedEvent;
	protected String bornIn;
	protected String desc;

	public HistoricalFigure(String name, String died, String born, String bornIn, String desc) {
		super(name);
		this.died = died;
		this.born = born;
		this.bornIn = bornIn;
		this.desc = desc;
	}

}
