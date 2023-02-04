package link;

import java.util.ArrayList;

import model.Era;
import model.HistoricalEvent;
import model.HistoricalFigure;

public class LinkFigure implements Runnable {
	private ArrayList<HistoricalFigure> listFigureDataRaw;
	private ArrayList<Era> listEraDataRaw;
	private ArrayList<HistoricalEvent> listEventDataRaw;
	private ArrayList<HistoricalFigure> listFigureData;

	public LinkFigure(ArrayList<HistoricalFigure> listFigureDataRaw, ArrayList<Era> listEraDataRaw,
			ArrayList<HistoricalEvent> listEventDataRaw, ArrayList<HistoricalFigure> listFigureData) {
		this.listFigureDataRaw = listFigureDataRaw;
		this.listEraDataRaw = listEraDataRaw;
		this.listEventDataRaw = listEventDataRaw;
		this.listFigureData = listFigureData;
	}

	public int YearStringToInt(String year) {
		if (year.isEmpty() || year.contains("đến nay")) {
			return 999999;
		}
		int trCN = 1;
		if (year.contains("TCN")) {
			trCN = -1;
			year = year.replace("TCN", "");
		}
		if (year.contains("trCN")) {
			trCN = -1;
			year = year.replace("trCN", "");
		}
		if (year.contains("SCN")) {
			year = year.replace("SCN", "");
		}
		if (year.contains("-")) {
			year = year.split("-")[2];
		}
		if (year.contains("/")) {
			year = year.split("/")[2];
		}
		year = year.replaceAll("\\s+", "");
		int intYear = Integer.parseInt(year) * trCN;
		return intYear;
	}

	public void findEraAndEvent(HistoricalFigure curFigure) {
		if (curFigure.getBorn().isEmpty() && curFigure.getDied().isEmpty()) {
			listFigureData.add(curFigure);
		} else {
			int born = YearStringToInt(curFigure.getBorn());
			int died = YearStringToInt(curFigure.getDied());
			int eraId = 0;
			int eventId = 0;
			for (Era era : listEraDataRaw) {
				int eraFromYear = YearStringToInt(era.getFromYear());
				int eraToYear = YearStringToInt(era.getToYear());
				if (eraToYear <= 0 && eraFromYear > 0) {
					eraFromYear *= (-1);
				}
				if ((born <= eraFromYear && eraFromYear <= died && died <= eraToYear)
						|| (born >= eraFromYear && born <= eraToYear && died >= eraFromYear && died <= eraToYear)
						|| (eraFromYear <= born && born <= eraToYear && died >= eraToYear)
						|| (born <= eraFromYear && died >= eraToYear)) {
					eraId = era.getId();
					curFigure.addRelatedEraId(eraId);
				}
			}
			for (HistoricalEvent event : listEventDataRaw) {
				int eventFromYear = YearStringToInt(event.getStartDate());
				int eventToYear = YearStringToInt(event.getEndDate());
				if (eventToYear <= 0 && eventFromYear > 0) {
					eventFromYear *= (-1);
				}
				if ((born <= eventFromYear && eventFromYear <= died && died <= eventToYear)
						|| (born >= eventFromYear && born <= eventToYear && died >= eventFromYear
								&& died <= eventToYear)
						|| (eventFromYear <= born && born <= eventToYear && died >= eventToYear)
						|| (born <= eventFromYear && died >= eventToYear)) {
					eventId = event.getId();
					curFigure.addRelatedEventId(eventId);
				}
			}

			this.listFigureData.add(curFigure);
		}
	}

	public void run() {
		System.out.println("Link Figure");
		for (HistoricalFigure figure : listFigureDataRaw) {
			findEraAndEvent(figure);
		}
	}
}