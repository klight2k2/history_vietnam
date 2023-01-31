package crawl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Era;

public class CrawlEra extends Crawler<Era> implements Crawling {
	public CrawlEra() {
		// TODO Auto-generated constructor stub
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
			String fromYear="";
			String toYear="";
			if (context.length > 1) {
				fromYear = context[1].split("[-–]")[0];
				toYear = context[1].split("[-–]")[1].replace(")", "");
				System.out.println(eraName + " " + fromYear);
				
			}
			Era era= new Era(eraName, fromYear, toYear);
			this.addDataCrawl(era);
			

		}
		String filePath = "D:\\oop2\\OOP\\vietnamHistory\\src\\crawl\\era.json";
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
		CrawlEra des = new CrawlEra();
		try {
			des.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
