package crawl.crawlfestival;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawl.Crawler;
import crawl.Crawling;
import model.Festival;

public class CrawlThanhHoaFestival extends Crawler<Festival> implements Crawling {

	public CrawlThanhHoaFestival(ArrayList<Festival> listDataCrawl) {
		// TODO Auto-generated constructor stub
		this.listDataCrawl = listDataCrawl;

	}

	@Override
	public void start() throws IOException {
		// TODO Auto-generated method stub
		String url = "https://vinpearl.com/vi/tong-hop-cac-le-hoi-thanh-hoa-truyen-thong-tung-thang-trong-nam";
		this.getHTML(url);
		Elements listTitle = this.doc.select(".content > h3");
		for (Element title : listTitle) {

			String festivalName = title.text().replaceAll("\\d{1,2}.\\d{1,2}.", "");
//		festivalName = festivalName.replaceAll("\\d{1,2}. ", "");
			if (festivalName.contains("Thanh Hóa"))
				continue;
			System.out.println(festivalName);
			String holdTime = title.nextElementSibling().select("li").get(0).text().substring("Thời gian:".length());
			String location = title.nextElementSibling().select("li").get(1).text().substring("Địa điểm: ".length());
			String imageLink = title.nextElementSibling().nextElementSibling().attr("src")
					+ title.nextElementSibling().nextElementSibling().nextElementSibling().attr("src")
					+ title.nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling()
							.attr("src");
			String doiTuongSuyTon = "";
			String desc = title.nextElementSibling().nextElementSibling().text();
			Festival festival = new Festival(festivalName, holdTime, location, doiTuongSuyTon, desc, imageLink);
			this.addDataCrawl(festival);
		}
	}

}
