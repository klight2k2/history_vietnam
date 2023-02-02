package crawl.crawlera;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import crawl.Crawler;
import crawl.Crawling;
import model.Era;

public class CrawlVNDoc extends Crawler<Era> implements Crawling{
	
	public CrawlVNDoc(ArrayList listDataCrawl) {
		super(listDataCrawl);
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
				System.out.println(eraName + " " + fromYear);

			}
//			int id = this.listDataCrawl.size() + 1;
			Era era = new Era(eraName, fromYear, toYear);
			this.addDataCrawl(era);

		}
		String filePath = "src\\data\\era.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(this.listDataCrawl, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
