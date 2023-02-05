package crawl.crawlfestival;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawl.Crawler;
import crawl.Crawling;
import model.Festival;

public class CrawlQuangNinhFestival extends Crawler<Festival> implements Crawling {
	public CrawlQuangNinhFestival(ArrayList<Festival> listDataCrawl) {
		this.listDataCrawl = listDataCrawl;
	}

	@Override
	public void start() throws IOException {
		String url = "https://vinpearl.com/vi/10-le-hoi-o-quang-ninh-thu-hut-dong-dao-du-khach-toi-tham-du?fbclid=IwAR39y4AiyTefJbP8QoIEy84lh4fI1UdSTiR52e49xnBNOtCek8BdCiBc38U";
		this.getHTML(url);
		this.doc.getElementById(url);
		Element mainContent = this.doc.getElementsByClass("content").first();
		Element widget = mainContent.getElementsByClass("widget-toc").first();
		int numsOfFes = widget.getElementsByTag("li").size();
		Elements title = mainContent.getElementsByTag("h2");
		Elements detail = mainContent.getElementsByTag("ul");
		Elements image = mainContent.getElementsByTag("img");

		for (int i = 0; i < numsOfFes - 1; i++) {
			String name = title.get(i).text().substring(2).trim();
			Elements tmp = detail.get(i).getElementsByTag("li");
			String location = tmp.get(0).text().split(": ")[1];
			String time = tmp.get(1).text().split(": ")[1];
			String desc = image.get(i).attr("data-caption").replace(" (Ảnh: Sưu tầm)", "").replaceAll("&nbsp", "")
					.replaceAll(";", "");
			Festival fes = new Festival(name, time, location, "", desc);
			this.listDataCrawl.add(fes);
		}
	}
}
