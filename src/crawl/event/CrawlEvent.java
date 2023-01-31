package crawl.event;

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
import model.HistoricalEvent;

public class CrawlEvent extends Crawler<HistoricalEvent> implements Crawling {
	public CrawlEvent() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start() throws IOException {
		// TODO Auto-generated method stub
		String url = "https://nguoikesu.com/tu-lieu/tom-luoc-lich-su-viet-nam";
		this.getHTML(url);
		Element mainTag = this.doc.getElementsByClass("com-content-article__body").get(0);
		Elements mainParagraphs = mainTag.getElementsByTag("p");
		System.out.println(mainParagraphs.size());
		mainParagraphs.remove(0);
		String startDate = "";
		String endDate = "";
		String desc = "";
		for (int i = 0; i < mainParagraphs.size(); i++) {
			Elements times = mainParagraphs.get(i).getElementsByTag("strong");
			System.out.println(times);
			switch (i) {
			case 0, 3, 6, 11, 29, 57, 59, 60, 63, 64, 65, 66, 67, 68, 69, 71, 72, 73, 75, 77, 80, 81, 82, 83, 84, 89,101,
					102, 104, 107, 108, 109, 110, 111, 115,114,112:
				startDate = times.get(0).text();
				endDate = times.get(0).text();
				desc = mainParagraphs.get(i).text();
				break;
			case 113,106,103:
				startDate = times.get(0).text() + "/" + times.get(1).text() + "/" + times.get(2).text();
				endDate = startDate;
				desc = mainParagraphs.get(i).text();
				break;
			default:
				startDate = times.get(0).text();
				System.out.println(i+"died");
				endDate = times.get(1).text();
				desc = mainParagraphs.get(i).text();
				break;
			}
			HistoricalEvent event=new HistoricalEvent(desc, startDate, endDate);
			this.addDataCrawl(event);
		}
		String filePath = "D:\\oop2\\OOP\\vietnamHistory\\src\\crawl\\event.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(this.listDataCrawl, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		CrawlEvent des = new CrawlEvent();
		try {
			des.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
