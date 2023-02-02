package crawl.crawlera;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawl.Crawler;
import crawl.Crawling;
import model.Era;

public class CrawlVNDoc extends Crawler<Era> implements Crawling {

	public CrawlVNDoc(ArrayList<Era> listDataCrawl) {
		// TODO Auto-generated constructor stub
		this.listDataCrawl = listDataCrawl;
	}

	@Override
	public void start() throws IOException {
		String url = "https://vndoc.com/lich-su-viet-nam";
		this.getHTML(url);
		Element mainPage = doc.getElementsByClass("container textview").get(0);
		Elements headers = mainPage.getElementsByTag("h2");
		Elements paragraphs = mainPage.getElementsByTag("p");
		for (Element p : paragraphs) {
			String context = p.html();
		}
		headers.remove(0);
//		headers.remove(headers.size()-1);
		for (Element header : headers) {
			String[] context = header.text().replaceAll("[0-9]{0,2}[.]", " ").split("[(]");
			String eraName = context[0];
			String fromYear = "";
			String toYear = "";
			if (context.length > 1) {
				fromYear = context[1].split("[-–]")[0];
				toYear = context[1].split("[-–]")[1].replace(")", "");

			}
//			int id = this.listDataCrawl.size() + 1;
			Era era = new Era(eraName, fromYear, toYear);
			System.out.println("Crawl Era: " + era.getName());
			this.addDataCrawl(era);

		}

	}
}
