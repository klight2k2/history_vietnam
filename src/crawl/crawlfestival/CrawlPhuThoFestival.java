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
import model.Festival;

public class CrawlPhuThoFestival extends Crawler<Festival> implements Crawling {

	public CrawlPhuThoFestival(ArrayList<Festival> listDataCrawl) {
		// TODO Auto-generated constructor stub
		this.listDataCrawl = listDataCrawl;
	}

	@Override
	public void start() throws IOException {
		String url = "http://phutho.tintuc.vn/tin-tuc/tong-hop-cac-le-hoi-chinh-o-phu-tho.html";
		this.getHTML(url);
		Element mainContent = this.doc.getElementById("articleContent");
		Elements paragraphs = mainContent.select("p");
		for (int i = 0; i < paragraphs.size(); i++) {
			
			Element p = paragraphs.get(i);
			if(!p.text().contains("Đặc điểm")) {
				continue;
			}
			String festivalName = "";
			String holdTime = "";
			String location = "";
			String doiTuongSuyTon = "";
			String desc = "";
			//
			if (i == 0) {
				festivalName = p.getElementsByTag("strong").get(1).text();
				System.out.println(p.html().substring(p.html().indexOf("<br>") + 4));
			} else {
				festivalName = p.getElementsByTag("strong").get(0).text();
			}
			String[] data = p.html().split("<br>");

			for (String d : data) {
//				System.out.println(d);
				String[] dataParts = d.split(":");
				String label = "";
				String contentString = "";
				
				if (dataParts.length == 2) {
					label = label.concat(dataParts[0]).trim();
					contentString = contentString.concat(dataParts[1]);
					System.out.println(label);
					switch (label) {
					case "Thời gian": {
						holdTime = holdTime.concat(contentString);
						System.out.println(holdTime);
						holdTime = holdTime.trim();
						break;
					}
					case "Địa điểm": {
						location = location.concat(contentString);
						location = location.trim();
						break;
					}
					case "Đối tượng suy tôn": {
						doiTuongSuyTon = doiTuongSuyTon.concat(contentString);
						doiTuongSuyTon = doiTuongSuyTon.trim();
						break;
					}
					case "Đặc điểm": {
						desc = desc.concat(contentString);
						desc = desc.trim();
						break;
					}

					}
				}
			}

			Festival festival = new Festival(festivalName, holdTime, location, doiTuongSuyTon, desc);
			this.addDataCrawl(festival);
			// System.out.println(paragraphs.html().split("<br>")[0]);
			// System.out.println(festivalName);
		}

		String filePath = "D:\\history_vietnam\\src\\data\\festival.json";
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(this.listDataCrawl, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		;
	}

//	public static void main(String[] args) {
//		CrawlPhuThoFestival des = new CrawlPhuThoFestival();
//		try {
//			des.start();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
