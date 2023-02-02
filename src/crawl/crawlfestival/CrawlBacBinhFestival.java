package crawl.crawlfestival;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawl.Crawler;
import crawl.Crawling;
import model.Festival;

public class CrawlBacBinhFestival extends Crawler<Festival> implements Crawling {

	public CrawlBacBinhFestival(ArrayList<Festival> listDataCrawl) {
		// TODO Auto-generated constructor stub
		this.listDataCrawl = listDataCrawl;
	}

	@Override
	public void start() throws IOException {
		String url = "https://alltours.vn/bac-ninh/nhung-le-hoi-o-bac-ninh.html";
		this.getHTML(url);
		Element mainContent = this.doc.getElementsByClass("single-blog-content entry clr").first();
		Elements festivalNames = mainContent.select("h2");
		Elements paragraphs = mainContent.select("p:has(br)");
		for (int i = 0; i < festivalNames.size(); i++) {
			Element p = paragraphs.get(i);
			String festivalName = festivalNames.get(i).text();
			festivalName = festivalName.replaceAll("\\d{1,2}. ", "");
			System.out.println(festivalName);
			String holdTime = "";
			String location = "";
			String doiTuongSuyTon = "";
			String desc = "";
			if (p != null) {
				String content = p.html();
				content = content.replace("hoàng:", "hoàng");
				String[] data = content.split("<br>");
				for (String d : data) {
					System.out.println(d);
					String[] dataParts = d.split(": ");
					String label = "";
					String contentString = "";
					label = label.concat(dataParts[0]);
					contentString = contentString.concat(dataParts[1]);
					System.out.println(label);
					System.out.println(contentString);
					switch (label) {
					case "Thời gian": {
						holdTime = holdTime.concat(contentString);
						holdTime = holdTime.trim();
						break;
					}
					case " Địa điểm": {
						location = location.concat(contentString);
						location = location.trim();
						break;
					}
					case " Đối tượng suy tôn": {
						if (contentString.contains("Truyền")) {
							int index = contentString.indexOf("Nguyễn");
							contentString = contentString.substring(index);
						}
						doiTuongSuyTon = doiTuongSuyTon.concat(contentString);
						doiTuongSuyTon = doiTuongSuyTon.trim();
						break;
					}
					case " Đặc điểm": {
						desc = desc.concat(contentString);
						desc = desc.trim();
						break;
					}
					}
				}
			}
			Festival festival = new Festival(festivalName, holdTime, location, doiTuongSuyTon, desc);
			this.addDataCrawl(festival);
		}

	}

}
