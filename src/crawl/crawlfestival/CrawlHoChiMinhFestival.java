
package crawl.crawlfestival;

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
import crawl.crawlfigure.CrawlFigure;
import model.Festival;
import model.HistoricalFigure;

public class CrawlHoChiMinhFestival extends Crawler<Festival> implements Crawling {

	public CrawlHoChiMinhFestival(ArrayList<Festival> listDataCrawl) {
		// TODO Auto-generated constructor stub
		this.listDataCrawl = listDataCrawl;

	}

	@Override
	public void start() throws IOException {
		// TODO Auto-generated method stub
		String url = "https://vinpearl.com/vi/10-le-hoi-hcm-dac-sac-nen-tham-du-it-nhat-mot-lan-trong-doi";
		this.getHTML(url);
		Elements listTitle = this.doc.select(".content > h2");
		for(Element title:listTitle) {
			
		String festivalName = title.text().replaceAll("\\d{1,2}. ", "").trim();
//		festivalName = festivalName.replaceAll("\\d{1,2}. ", "");
		
		String holdTime = title.nextElementSibling().select("li").get(0).text().split(":")[1].trim();
		String location = title.nextElementSibling().select("li").get(1).text().split(":")[1].trim();
		String imageLink= title.nextElementSibling().nextElementSibling().attr("src");
		String doiTuongSuyTon = "";
		String desc = title.nextElementSibling().nextElementSibling().nextElementSibling().text()+title.nextElementSibling().nextElementSibling().nextElementSibling().nextElementSibling().text();
		Festival festival= new Festival(festivalName, holdTime, location, doiTuongSuyTon, desc,imageLink);
		this.addDataCrawl(festival);
		}
		 String filePath = "D:\\history_vietnam\\src\\data\\tes.json";
		 Gson gson = new GsonBuilder().setPrettyPrinting().create();
		 try {
		 	FileWriter writer = new FileWriter(new File(filePath));
		 	gson.toJson(this.listDataCrawl, writer);
		 	writer.close();
		 } catch (IOException e) {
		 	e.printStackTrace();
		 }
		System.out.println(listTitle.next().next());
	}
	
	public static void main(String[] args) {
		CrawlHoChiMinhFestival des = new CrawlHoChiMinhFestival(new ArrayList<Festival>());
		try {
			des.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
