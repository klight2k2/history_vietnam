package model;

import java.util.*;

import com.google.gson.Gson;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class MainModel {
	private  List<HistoricalFigure> HistoricalFigures = new ArrayList<>();
	private  List<Era> Eras = new ArrayList<>();
	private  List<Festival> Festivals = new ArrayList<>();
	private  List<HistoricalSite> HistoricSites = new ArrayList<>();
	private  List<HistoricalEvent> Events = new ArrayList<>();

	public MainModel(){
		try{
			Gson gson = new Gson();

			Era Eras_Temp[] = null;
			FileReader reader_era = new FileReader("/home/tienviper/Desktop/oop/history_vietnam/src/data/era.json");
			Eras_Temp = gson.fromJson(reader_era, Era[].class);
			for (Era p : Eras_Temp) {
				Eras.add(p);
			}

			Festival Festival_Temp[] = null;
			FileReader reader_festival = new FileReader("/home/tienviper/Desktop/oop/history_vietnam/src/data/festival.json");
			Festival_Temp = gson.fromJson(reader_festival, Festival[].class);
			for (Festival p : Festival_Temp) {
				Festivals.add(p);
			}

			HistoricalEvent HistoricalEvent_Temp[] = null;
			FileReader reader_event = new FileReader("/home/tienviper/Desktop/oop/history_vietnam/src/data/event.json");
			HistoricalEvent_Temp = gson.fromJson(reader_event, HistoricalEvent[].class);
			for (HistoricalEvent p : HistoricalEvent_Temp) {
				Events.add(p);
			}

			HistoricalFigure HistoricalFigure_Temp[] = null;
			FileReader reader_figure = new FileReader("/home/tienviper/Desktop/oop/history_vietnam/src/data/figure.json");
			HistoricalFigure_Temp = gson.fromJson(reader_figure, HistoricalFigure[].class);
			for (HistoricalFigure p : HistoricalFigure_Temp) {
				HistoricalFigures.add(p);
			}

			HistoricalSite HistoricalSite_Temp[] = null;
			FileReader reader_site = new FileReader("/home/tienviper/Desktop/oop/history_vietnam/src/data/site.json");
			HistoricalSite_Temp = gson.fromJson(reader_site, HistoricalSite[].class);
			for (HistoricalSite p : HistoricalSite_Temp) {
				HistoricSites.add(p);
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public List<HistoricalFigure> getHistoricalFigures() {

		return HistoricalFigures;
	}

	public List<Era> getEras() {
		return this.Eras;
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

	public static void main(String[] args) {
		MainModel mainModel = new MainModel();
	}
}
