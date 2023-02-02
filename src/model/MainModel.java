package model;

import java.util.ArrayList;
import java.util.List;

public class MainModel {
	private  List<HistoricalFigure> HistoricalFigures = new ArrayList<>();
	private  List<Era> Eras = new ArrayList<>();
	private  List<Festival> Festivals = new ArrayList<>();
	private  List<HistoricalSite> HistoricSites = new ArrayList<>();
	private  List<HistoricalEvent> Events = new ArrayList<>();

	public MainModel() {
//		this.Events = null;
//		this.HistoricalFigures = null;
//		this.Eras = null;
//		this.Festivals = null;
//		//	read data from json
//		this.HistoricSites = null;
//		this.Events = null;
	}

	public List<HistoricalFigure> getHistoricalFigures() {
		return HistoricalFigures;
	}

	public List<Era> getEras() {
		return Eras;
	}

	public List<Festival> getFestivals() {
		return Festivals;
	}

	public List<HistoricalSite> getHistoricSites() {
		return HistoricSites;
	}

	public List<HistoricalEvent> getEvents() {
		return Events;
	}


}
