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
		this.listWebCrawl.add(new CrawlAnGiangFestival(this.listDataCrawl));
		this.listWebCrawl.add(new CrawlBacNinhFestival(this.listDataCrawl));
		this.listWebCrawl.add(new CrawlDaNangFestival(this.listDataCrawl));
		this.listWebCrawl.add(new CrawlHaNamFestival(this.listDataCrawl));
		this.listWebCrawl.add(new CrawlHoChiMinhFestival(this.listDataCrawl));
		this.listWebCrawl.add(new CrawlHueFestival(this.listDataCrawl));
		this.listWebCrawl.add(new CrawlHoChiMinhFestival(this.listDataCrawl));
		this.listWebCrawl.add(new CrawlNgheAnFestival(this.listDataCrawl));
		this.listWebCrawl.add(new CrawlPhuThoFestival(this.listDataCrawl));
//		this.listWebCrawl.add(new CrawlQuangNinhFestival(this.listDataCrawl));
		this.listWebCrawl.add(new CrawlTuyenQuangFestival(this.listDataCrawl));
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

}
