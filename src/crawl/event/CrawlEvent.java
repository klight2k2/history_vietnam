package crawl.event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import crawl.Crawling;
import model.HistoricalEvent;

public class CrawlEvent implements Runnable {
	private ArrayList<Crawling> listWebCrawl = new ArrayList<>();
	private ArrayList<HistoricalEvent> listDataCrawl = new ArrayList<>();

	public CrawlEvent() {
		// TODO Auto-generated constructor stub
		this.listWebCrawl.add(new CrawlEventNguoiKeSu(listDataCrawl));
	}

	@Override
	public void run() {
		for (Crawling web : listWebCrawl) {
			try {
				web.start();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		System.out.println("Crawl completed...");
		String filePath = "src\\data\\event.json";
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
			des.run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
