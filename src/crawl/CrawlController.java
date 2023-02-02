package crawl;

import crawl.crawlsite.CrawlSite;

public class CrawlController {

	public CrawlController() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
       
        CrawlSite crawlsite = new CrawlSite();
        Thread siteThread = new Thread(crawlsite);
        siteThread.start();
    }
}
