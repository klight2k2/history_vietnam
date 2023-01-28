package history;

public class HistoricalFigure extends Historical {
	protected String born;
	protected String died;
	protected Era livedInEras;
	protected HistoricalEvent relatedEvent;
	public HistoricalFigure(String name,String died,String born) {
		super(name);
		this.died=died;
		this.born=born;
	}

}
