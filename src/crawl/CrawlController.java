package crawl;

import java.util.ArrayList;

import crawl.crawlera.CrawlEra;
import crawl.crawlfestival.CrawlFestival;
import crawl.crawlfigure.CrawlFigure;
import crawl.crawlsite.CrawlSite;
import crawl.event.CrawlEvent;
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

	public CrawlController() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		CrawlController controller = new CrawlController();

		// Crawl data
		CrawlEra crawlEra = new CrawlEra(controller.listEraData);
		Thread eraThread = new Thread(crawlEra);

		CrawlFestival crawlFestival = new CrawlFestival(controller.listFestivalData);
		Thread festivalThread = new Thread(crawlFestival);

		CrawlFigure crawlFigure = new CrawlFigure(controller.listFigureData);
		Thread figureThread = new Thread(crawlFigure);

		CrawlSite crawlSite = new CrawlSite(controller.listSiteData);
		Thread siteThread = new Thread(crawlSite);

		CrawlEvent crawlEvent = new CrawlEvent(controller.listEventData);
		Thread eventThread = new Thread(crawlEvent);

		eraThread.start();
		festivalThread.start();
		figureThread.start();
		siteThread.start();
		eventThread.start();

		// Link data

//		System.out.println("Crawl completed...");
//		String filePath = "src\\data\\era.json";
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		try {
//			FileWriter writer = new FileWriter(new File(filePath));
//			gson.toJson(this.listDataCrawl, writer);
//			writer.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

	}
}
