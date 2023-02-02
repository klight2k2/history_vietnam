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

	public static void main(String[] args) {
		CrawlController controller = new CrawlController();

		// Crawl data
		CrawlEra crawlEra = new CrawlEra(controller.listEraDataRaw);
//		Thread eraThread = new Thread(crawlEra);

		CrawlFestival crawlFestival = new CrawlFestival(controller.listFestivalDataRaw);
//		Thread festivalThread = new Thread(crawlFestival);

		CrawlFigure crawlFigure = new CrawlFigure(controller.listFigureDataRaw);
//		Thread figureThread = new Thread(crawlFigure);/

		CrawlSite crawlSite = new CrawlSite(controller.listSiteDataRaw);
//		Thread siteThread = new Thread(crawlSite);

		CrawlEvent crawlEvent = new CrawlEvent(controller.listEventDataRaw);
//		Thread eventThread = new Thread(crawlEvent);

		ExecutorService executorCrawl = Executors.newCachedThreadPool();
		executorCrawl.execute(crawlEra);
		executorCrawl.execute(crawlFestival);
		executorCrawl.execute(crawlFigure);
		executorCrawl.execute(crawlSite);
		executorCrawl.execute(crawlEvent);
		executorCrawl.shutdown();

		controller.waitForThread(executorCrawl);
		System.out.println("Crawl completed...");

		// Link data
		LinkEra linkEra = new LinkEra(controller.listEraDataRaw, controller.listEraData);
		linkEra.run();

//		ExecutorService executorLink = Executors.newCachedThreadPool();
//		executorLink.execute(linkEra);
//
//		executorLink.shutdown();
//		controller.waitForThread(executorLink);

		System.out.println("Link completed...");

		// Write data to JSON
		String filePath = "src\\data\\";
		controller.saveFile(filePath + "era.json", controller.listEraData);
		controller.saveFile(filePath + "festival.json", controller.listFestivalDataRaw);
		controller.saveFile(filePath + "figure.json", controller.listFigureDataRaw);
		controller.saveFile(filePath + "site.json", controller.listSiteDataRaw);
		controller.saveFile(filePath + "event.json", controller.listEventDataRaw);
		System.out.println("Write file completed...");
	}
}
