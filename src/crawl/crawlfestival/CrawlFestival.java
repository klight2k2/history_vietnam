package crawl.crawlfestival;

import java.io.IOException;
import java.util.ArrayList;

import crawl.Crawling;
import model.Festival;

public class CrawlFestival implements Runnable {
	private ArrayList<Crawling> listWebCrawl = new ArrayList<>();
	private ArrayList<Festival> listDataCrawl;

	public CrawlFestival(ArrayList<Festival> listDataCrawl) {
		this.listDataCrawl = listDataCrawl;

		CrawlAnGiangFestival angiang = new CrawlAnGiangFestival(this.listDataCrawl);
		this.listWebCrawl.add(angiang);
	}

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
		//CrawlFestival des = new CrawlFestival();
		//des.run();
	}

}
