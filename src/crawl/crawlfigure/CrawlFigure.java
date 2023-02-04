package crawl.crawlfigure;

import java.io.IOException;
import java.util.ArrayList;

import crawl.Crawling;
import model.HistoricalFigure;

public class CrawlFigure implements Runnable {
	private ArrayList<Crawling> listWebCrawl = new ArrayList<>();
	private ArrayList<HistoricalFigure> listDataCrawl;
	public CrawlFigure(ArrayList<HistoricalFigure> listDataCrawl) {
		// TODO Auto-generated constructor stub
		this.listDataCrawl = listDataCrawl;
		this.listWebCrawl.add(new CrawlFigureNguoiKeSu(listDataCrawl));
		this.listWebCrawl.add(new CrawlFigureFromVanSu(listDataCrawl));
	}

	@Override
	public void run() {
		for (Crawling web : listWebCrawl) {
			try {
				web.start();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
	CrawlFigure des = new CrawlFigure(new ArrayList<HistoricalFigure>());
	des.run();
}
}
