package crawl.crawlfestival;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import crawl.Crawling;
import model.Festival;

public class CrawlFestival implements Runnable {
	private ArrayList<Crawling> listWebCrawl = new ArrayList<>();
	private ArrayList<Festival> listDataCrawl = new ArrayList<>();

	public CrawlFestival() {
		CrawlAnGiangFestival angiang = new CrawlAnGiangFestival(this.listDataCrawl);
		this.listWebCrawl.add(angiang);
	}

	public void run() {
		for (Crawling webCrawl : listWebCrawl) {
			try {
				webCrawl.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Crawl completed...");
		String filePath = "src\\data\\festival.json";
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
		CrawlFestival des = new CrawlFestival();
		des.run();
	}

}
