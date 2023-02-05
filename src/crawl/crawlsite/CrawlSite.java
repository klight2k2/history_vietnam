package crawl.crawlsite;

import java.io.IOException;
import java.util.ArrayList;

import crawl.Crawling;
import model.HistoricalSite;

public class CrawlSite implements Runnable {
	private ArrayList<Crawling> listWebCrawl = new ArrayList<>();
	private ArrayList<HistoricalSite> listDataCrawl;

	public CrawlSite(ArrayList<HistoricalSite> listDataCrawl) {
		// TODO Auto-generated constructor stub
		this.listDataCrawl = listDataCrawl;

		listWebCrawl.add(new CrawlFromDitich(this.listDataCrawl));
//		listWebCrawl.add(new CrawlFromWiki(this.listDataCrawl));

	}

	@Override
	public void run() {
		for (Crawling webCrawl : listWebCrawl) {
			try {
				webCrawl.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		CrawlSite des = new CrawlSite(new ArrayList<HistoricalSite>());
		des.run();
	}
}
