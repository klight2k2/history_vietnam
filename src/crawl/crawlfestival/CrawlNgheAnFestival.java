
package crawl.crawlfestival;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawl.Crawler;
import crawl.Crawling;
import model.Festival;

public class CrawlNgheAnFestival extends Crawler<Festival> implements Crawling {

	public CrawlNgheAnFestival(ArrayList<Festival> listDataCrawl) {
		// TODO Auto-generated constructor stub
		this.listDataCrawl = listDataCrawl;

	}

	@Override
	public void start() throws IOException {
		// TODO Auto-generated method stub
		String url = "https://vinpearl.com/vi/le-hoi-o-nghe-an-diem-danh-10-le-hoi-doc-dao-nhat-xu-nghe";
		this.getHTML(url);
		Elements listTitle = this.doc.select(".content > h2");
		for (Element title : listTitle) {

			String festivalName = title.text().replaceAll("\\d{1,2}. ", "");
//		festivalName = festivalName.replaceAll("\\d{1,2}. ", "");

			String holdTime = title.nextElementSibling().select("li").get(0).text().substring("Thời gian:".length());
			String location = title.nextElementSibling().select("li").get(1).text().substring("Địa điểm: ".length());
			String imageLink = title.nextElementSibling().nextElementSibling().attr("src");
			String doiTuongSuyTon = "";
			String desc = title.nextElementSibling().nextElementSibling().nextElementSibling().text()
					+ title.nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling().text();
			Festival festival = new Festival(festivalName, holdTime, location, doiTuongSuyTon, desc, imageLink);
			this.addDataCrawl(festival);
		}
	}

}
