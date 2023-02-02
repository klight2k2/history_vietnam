package crawl;

import crawl.crawlera.CrawlEra;
import crawl.crawlfestival.CrawlFestival;
import crawl.crawlfigure.CrawlFigure;
import crawl.crawlsite.CrawlSite;
import crawl.event.CrawlEvent;

public class CrawlController {

	public CrawlController() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
        
        CrawlEra crawlEra = new CrawlEra();
        Thread eraThread = new Thread(crawlEra);
        
        CrawlFestival crawlFestival = new CrawlFestival();
        Thread festivalThread = new Thread(crawlFestival);
        
        CrawlFigure crawlFigure = new CrawlFigure();
        Thread figureThread = new Thread(crawlFigure);
    
        CrawlSite crawlSite = new CrawlSite();
        Thread siteThread = new Thread(crawlSite);

        CrawlEvent crawlEvent = new CrawlEvent();
        Thread eventThread = new Thread(crawlEvent);

        eraThread.start();
        festivalThread.start();
        figureThread.start();
        siteThread.start();
        eventThread.start();
    }
}
