package crawl.event;

import java.util.ArrayList;

import crawl.Crawling;
import model.HistoricalEvent;

public class CrawlEvent implements Runnable {
	private ArrayList<Crawling> listWebCrawl = new ArrayList<>();
	private ArrayList<HistoricalEvent> listDataCrawl;

	public CrawlEvent(ArrayList<HistoricalEvent> listDataCrawl) {
		// TODO Auto-generated constructor stub
		this.listDataCrawl = listDataCrawl;

		this.listWebCrawl.add(new CrawlEventNguoiKeSu(listDataCrawl));
	}

	@Override
	public void run() {
		for (Crawling web : listWebCrawl) {
			try {
				web.start();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
