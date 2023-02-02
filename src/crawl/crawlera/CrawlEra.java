package crawl.crawlera;

import java.util.ArrayList;

import crawl.Crawling;
import model.Era;

public class CrawlEra implements Runnable {
	private ArrayList<Crawling> listWebCrawl = new ArrayList<>();
	private ArrayList<Era> listDataCrawl;

	public CrawlEra(ArrayList<Era> listDataCrawl) {
		this.listDataCrawl = listDataCrawl;

		CrawlVNDoc vndoc = new CrawlVNDoc(this.listDataCrawl);
		this.listWebCrawl.add(vndoc);
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
		//CrawlEra des = new CrawlEra();
		//des.run();
	}
}
