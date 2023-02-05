
package crawl.crawlfestival;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawl.Crawler;
import crawl.Crawling;
import model.Festival;

public class CrawlHueFestival extends Crawler<Festival> implements Crawling {

	public CrawlHueFestival(ArrayList<Festival> listDataCrawl) {
		// TODO Auto-generated constructor stub
		this.listDataCrawl = listDataCrawl;

	}

	@Override
	public void start() throws IOException {
		// TODO Auto-generated method stub
		String url = "https://vinpearl.com/vi/le-hoi-hue-kham-pha-nhung-su-kien-van-hoa-doc-dao-dat-co-do";
		this.getHTML(url);
		Boolean first = false;
		Elements listTitle = this.doc.select(".content > h2");
		for (Element title : listTitle) {

			String festivalName = title.text().replaceAll("\\d{1,2}. ", "").trim();
//		festivalName = festivalName.replaceAll("\\d{1,2}. ", "");

			String holdTime = title.nextElementSibling().select("li").get(0).text().split(":")[1].trim();
			String location = title.nextElementSibling().select("li").get(1).text().split(":")[1].trim();
			String imageLink = title.nextElementSibling().nextElementSibling().nextElementSibling().attr("src");
			String doiTuongSuyTon = "";
			String desc = title.nextElementSibling().nextElementSibling().text();
			if (!first) {
				desc += title.nextElementSibling().nextElementSibling().nextElementSibling().text();
				imageLink = title.nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling()
						.attr("src");
			}
			Festival festival = new Festival(festivalName, holdTime, location, doiTuongSuyTon, desc, imageLink);
			this.addDataCrawl(festival);
		}
	}

}
