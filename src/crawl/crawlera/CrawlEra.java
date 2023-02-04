package crawl.crawlera;

import java.util.ArrayList;

import crawl.Crawling;
import crawl.crawlfigure.CrawlFigure;
import model.Era;

public class CrawlEra implements Runnable {
	private ArrayList<Crawling> listWebCrawl = new ArrayList<>();
	private ArrayList<Era> listDataCrawl;

	public CrawlEra(ArrayList<Era> listDataCrawl) {
		this.listDataCrawl = listDataCrawl;

		CrawlVNDoc vndoc = new CrawlVNDoc(this.listDataCrawl);
		this.listWebCrawl.add(vndoc);
		CrawlEraFromlVanSu vansu = new CrawlEraFromlVanSu(this.listDataCrawl);
		this.listWebCrawl.add(vansu);
		// TODO Auto-generated constructor stub
	}

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
		
		CrawlEra des = new CrawlEra(new ArrayList<Era>());
		des.run();
	}
}
