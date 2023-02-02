package crawl.crawlera;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import crawl.Crawler;
import crawl.Crawling;
import model.Era;

public class CrawlEra implements Runnable {
	private List<Crawling> listWebCrawl = new ArrayList<>();
	private ArrayList<Era> listData = new ArrayList<>();

	public CrawlEra() {
		CrawlVNDoc vndoc = new CrawlVNDoc(listData);
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
	}


	public static void main(String[] args) {
		CrawlEra des = new CrawlEra();
		des.run();
	}
}
