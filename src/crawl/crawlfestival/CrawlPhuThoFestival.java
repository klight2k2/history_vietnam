package crawl.crawlfestival;

import java.io.IOException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawl.Crawler;
import crawl.Crawling;
import crawl.crawlera.CrawlEra;
import model.Festival;

public class CrawlPhuThoFestival extends Crawler<Festival> implements Crawling {

	public CrawlPhuThoFestival() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start() throws IOException {
		String url = "http://phutho.tintuc.vn/tin-tuc/tong-hop-cac-le-hoi-chinh-o-phu-tho.html";
		this.getHTML(url);
		Element mainContent = this.doc.getElementById("articleContent");
		Elements paragraphs = mainContent.select("p");
		for (Element paragraph : paragraphs) {
//			System.out.println(paragraph.toString().matches("[0-9](.)");
			if (paragraph.toString().matches("[0-9](.)")) {
				System.out.println(paragraph);
			}
		}

	}

	public static void main(String[] args) {
		CrawlPhuThoFestival des = new CrawlPhuThoFestival();
		try {
			des.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
