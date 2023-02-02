package crawl.crawlera;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import crawl.Crawling;
import model.Era;

public class CrawlEra implements Runnable {
	private ArrayList<Crawling> listWebCrawl = new ArrayList<>();
	private ArrayList<Era> listDataCrawl = new ArrayList<>();

	public CrawlEra() {
		CrawlVNDoc vndoc = new CrawlVNDoc(this.listDataCrawl);
		this.listWebCrawl.add(vndoc);
		// TODO Auto-generated constructor stub
	}

	public void run() {
		for (Crawling web : listWebCrawl) {
			try {
				web.start();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		System.out.println("Crawl completed...");
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

	public static void main(String[] args) {
		CrawlEra des = new CrawlEra();
		des.run();
	}
}
