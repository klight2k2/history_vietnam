package crawl.crawlfestival;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawl.Crawler;
import crawl.Crawling;
import model.Festival;

public class CrawlFestival implements Runnable {
	private List<Crawling> listWebCrawl= new ArrayList<>();
	private List<Festival> lisData= new ArrayList<>();
	public CrawlFestival() {
		CrawlAnGiangFestival angiang = new CrawlAnGiangFestival(this.lisData);
		this.listWebCrawl.add(angiang);
	}

	public void run() {
		for( Crawling webCrawl: listWebCrawl) {
			try {
				webCrawl.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		CrawlFestival des = new CrawlFestival();
		des.run();
	}

}
