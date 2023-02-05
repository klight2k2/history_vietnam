package crawl;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import crawl.crawlera.CrawlEra;
import crawl.crawlfestival.CrawlFestival;
import crawl.crawlfigure.CrawlFigure;
import crawl.crawlsite.CrawlSite;
import crawl.event.CrawlEvent;
import link.LinkEra;
import link.LinkEvent;
import link.LinkFigure;
import model.Era;
import model.Festival;
import model.HistoricalEvent;
import model.HistoricalFigure;
import model.HistoricalSite;

public class CrawlController {
	private ArrayList<Era> listEraData = new ArrayList<>();
	private ArrayList<Festival> listFestivalData = new ArrayList<>();
	private ArrayList<HistoricalFigure> listFigureData = new ArrayList<>();
	private ArrayList<HistoricalSite> listSiteData = new ArrayList<>();
	private ArrayList<HistoricalEvent> listEventData = new ArrayList<>();

	private ArrayList<Era> listEraDataRaw = new ArrayList<>();
	private ArrayList<Festival> listFestivalDataRaw = new ArrayList<>();
	private ArrayList<HistoricalFigure> listFigureDataRaw = new ArrayList<>();
	private ArrayList<HistoricalSite> listSiteDataRaw = new ArrayList<>();
	private ArrayList<HistoricalEvent> listEventDataRaw = new ArrayList<>();

	public CrawlController() {
		// TODO Auto-generated constructor stub
	}

	public void saveFile(String path, ArrayList data) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(path));
			gson.toJson(data, writer);
			writer.flush();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void waitForThread(ExecutorService es) {
		try {
			boolean finished = es.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void crawl() {
		// Crawl data________________________________________________________________
		CrawlEra crawlEra = new CrawlEra(this.listEraDataRaw);
		CrawlFestival crawlFestival = new CrawlFestival(this.listFestivalDataRaw);
		CrawlFigure crawlFigure = new CrawlFigure(this.listFigureDataRaw);
		CrawlSite crawlSite = new CrawlSite(this.listSiteDataRaw);
		CrawlEvent crawlEvent = new CrawlEvent(this.listEventDataRaw);

		ExecutorService executorCrawl = Executors.newCachedThreadPool();
		executorCrawl.execute(crawlEra);
		executorCrawl.execute(crawlFestival);
		executorCrawl.execute(crawlFigure);
		executorCrawl.execute(crawlSite);
		executorCrawl.execute(crawlEvent);

		executorCrawl.shutdown();

		this.waitForThread(executorCrawl);
		System.out.println("Crawl completed...");
	}

	public void link() {
		// Link data________________________________________________________________
		LinkEra linkEra = new LinkEra(this.listEraDataRaw, this.listEraData);
		LinkEvent linkEvent = new LinkEvent(this.listEventDataRaw, this.listEraDataRaw, this.listEventData);
		LinkFigure linkFigure = new LinkFigure(this.listFigureDataRaw, this.listEraDataRaw, this.listEventDataRaw,
				this.listFigureData);

		ExecutorService executorLink = Executors.newCachedThreadPool();
		executorLink.execute(linkEra);
		executorLink.execute(linkEvent);
		executorLink.execute(linkFigure);

		executorLink.shutdown();

		this.waitForThread(executorLink);
		System.out.println("Link completed...");
	}

	public void saveToJson() {
		// Write data to JSON_______________________________________________________
		String filePath = "src\\data\\";
		this.saveFile(filePath + "era.json", this.listEraData);
		this.saveFile(filePath + "festival.json", this.listFestivalDataRaw);
		this.saveFile(filePath + "figure.json", this.listFigureData);
		this.saveFile(filePath + "site.json", this.listSiteDataRaw);
		this.saveFile(filePath + "event.json", this.listEventData);
		System.out.println("Write file completed...");
	}

	public void start() {
		crawl();
		link();
		saveToJson();
	}

	public static void main(String[] args) {
		CrawlController controller = new CrawlController();
		controller.start();
	}
}
