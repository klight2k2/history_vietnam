package crawl.crawlfigure;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import crawl.Crawler;
import crawl.Crawling;
import model.HistoricalFigure;

public class CrawlFigureFromVanSu  extends Crawler<HistoricalFigure> implements Crawling{
	private List<String> listUrlFigure= new ArrayList<>();
	public CrawlFigureFromVanSu(ArrayList<HistoricalFigure> listDataCrawl) {
		this.listDataCrawl = listDataCrawl;
	}

	@Override
	public void start() throws IOException {
		// TODO Auto-generated method stub
		String url="https://vansu.vn/viet-nam/viet-nam-nhan-vat?page=";
		for (int i = 1; i <=119; i++) {
			this.getHTML(url+i);
			Elements figureUrls= this.doc.select("tbody > tr > td > a:has(i)");
			for(Element figureUrl:figureUrls) {
				listUrlFigure.add("https://vansu.vn"+figureUrl.attr("href"));
//			System.out.println(
			}
			
		}
		for(int i=0;i<listUrlFigure.size();i++) {
			this.getHTML(this.listUrlFigure.get(i));
			String nameFigure =this.doc.getElementsByClass("active section").first().text();
			Elements listData= this.doc.select("tbody > tr");
			String desc = listData.last().text();
			String born = "";
			String died = "";
			String bornIn = "";
			String imageLink = "";
			String otherName="";
			String era="";
			for(Element data:listData) {
				if(data.text().contains("Tỉnh thành")) bornIn=data.select("td").get(1).text();
				if(data.text().contains("Năm sinh")) {
					born=data.select("td").get(1).text().split("-")[0];
					died=data.select("td").get(1).text().split("-")[1];
				}
				if(data.text().contains("Thời kì")){
					era=data.select("td").get(1).text();
				}
				if(data.text().contains("Tên khác")) {
					otherName=data.select("td").get(1).text();
				}
			}
			if(born=="" && died=="") {
				born=era;
				died=era;
			}
			HistoricalFigure figure= new HistoricalFigure(nameFigure, died, born, bornIn, desc);
			figure.addOtherName(otherName);
			System.out.println("Crawl Figure: " + figure.getName());
			this.addDataCrawl(figure);
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
