package crawl.crawlera;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import crawl.Crawler;
import crawl.Crawling;
import model.Era;

public class CrawlEraFromlVanSu extends Crawler<Era> implements Crawling{

	public CrawlEraFromlVanSu(ArrayList<Era> listDataCrawl) {
		this.listDataCrawl = listDataCrawl;
	}

	@Override
	public void start() throws IOException {
		String url = "https://vansu.vn/viet-nam/viet-nam-nhan-vat?";
		this.getHTML(url);
		Elements listEra=this.doc.select("body > div.ui.container > form > div > div.five.wide.inline.field > div > div.menu > div");
		System.out.println(listEra.text()+ "hello");
		listEra.remove(0);
		for(int i=0;i<listDataCrawl.size();i++) {
			this.listDataCrawl.get(i).addOtherName(listEra.get(i).text());
		}
		// String filePath = "D:\\history_vietnam\\src\\data\\tes.json";
		// Gson gson = new GsonBuilder().setPrettyPrinting().create();
		// try {
		// 	FileWriter writer = new FileWriter(new File(filePath));
		// 	gson.toJson(this.listDataCrawl, writer);
		// 	writer.close();
		// } catch (IOException e) {
		// 	e.printStackTrace();
		// }
	}

}
