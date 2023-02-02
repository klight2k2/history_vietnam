package crawl.crawlfigure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import crawl.Crawling;
import model.HistoricalFigure;

public class CrawlFigure implements Runnable {
	private ArrayList<Crawling> listWebCrawl = new ArrayList<>();
	private ArrayList<HistoricalFigure> listDataCrawl = new ArrayList<>();

	public CrawlFigure() {
		// TODO Auto-generated constructor stub
		this.listWebCrawl.add(new CrawlFigureNguoiKeSu(listDataCrawl));
	}

	@Override
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
		String filePath = "src\\data\\figure.json";
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
		CrawlFigure des = new CrawlFigure();
		des.run();
	}
}
