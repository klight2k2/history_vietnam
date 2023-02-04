package link;

import java.util.ArrayList;

import model.Era;
import model.HistoricalEvent;

public class LinkEvent implements Runnable {
	private ArrayList<HistoricalEvent> listEventDataRaw;
	private ArrayList<Era> listEraDataRaw;
	private ArrayList<HistoricalEvent> listEventData;

	public LinkEvent(ArrayList<HistoricalEvent> listEventDataRaw, ArrayList<Era> listEraDataRaw,
			ArrayList<HistoricalEvent> listEventData) {
		this.listEventDataRaw = listEventDataRaw;
		this.listEraDataRaw = listEraDataRaw;
		this.listEventData = listEventData;
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

	public void findEra(HistoricalEvent curEvent) {
		if (curEvent.getStartDate().isEmpty() && curEvent.getEndDate().isEmpty()) {
			listEventData.add(curEvent);
		} else {
			int relatedEraId = 0;
			for (Era era : listEraDataRaw) {
				int curEventStartDate = YearStringToInt(curEvent.getStartDate());
				int eraFromYear = YearStringToInt(era.getFromYear());
				int eraToYear = YearStringToInt(era.getToYear());
				if (eraToYear <= 0 && eraFromYear > 0) {
					eraFromYear *= (-1);
				}
				if (curEventStartDate >= eraFromYear && curEventStartDate <= eraToYear) {
					relatedEraId = era.getId();
					break;
				}
			}
			curEvent.setEra(relatedEraId);
//			System.out.println("Related Event " + curEvent.getId() + "to " + relatedEraId);
			listEventData.add(curEvent);
		}
	}

	public void run() {
		System.out.println("Link Event");
		for (HistoricalEvent event : listEventDataRaw) {
			findEra(event);
		}
	}
}
