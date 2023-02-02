package crawl.crawlsite;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import crawl.Crawling;
import model.HistoricalSite;

public class CrawlSite implements Runnable {
	private List<Crawling> listWebCrawl = new ArrayList<>();
	private ArrayList<HistoricalSite> listDataCrawl = new ArrayList<>();

	public CrawlSite() {
		// TODO Auto-generated constructor stub
		Crawling site1 = new CrawlFromDitich(this.listDataCrawl);
		listWebCrawl.add(site1);
		listWebCrawl.add(new CrawlFromWiki(this.listDataCrawl));

	}

	@Override
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
		String filePath = "src\\data\\site.json";
//		BufferedImage image = ImageIO.read(new URL("http://ditich.vn/upload/images/ditich/Ch-L0009.jpg"));
//		ImageIO.write(image, "png", new File("D:\\oop2\\OOP\\vietnamHistory\\data\\image.png"));
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(this.listDataCrawl, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println(listDetailUrl);

	}

	public static void main(String[] args) {
		CrawlSite des = new CrawlSite();
		des.run();
	}
}
