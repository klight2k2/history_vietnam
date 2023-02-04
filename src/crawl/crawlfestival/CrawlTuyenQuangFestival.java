package crawl.crawlfestival;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawl.Crawler;
import crawl.Crawling;
import model.Festival;

public class CrawlTuyenQuangFestival extends Crawler<Festival> implements Crawling {

	public CrawlTuyenQuangFestival(ArrayList<Festival> listDataCrawl) {
		// TODO Auto-generated constructor stub
		this.listDataCrawl = listDataCrawl;
	}

	@Override
	public void start() throws IOException {
		// TODO Auto-generated method stub
		String url = "https://alltours.vn/tuyen-quang/cac-le-hoi-o-tuyen-quang.html";
		this.getHTML(url);
		Element mainContent = this.doc.getElementsByClass("single-blog-content entry clr").first();
		Elements festivalNames = mainContent.select("h2");
		Elements paragraphs = mainContent.select("p:has(br)");
		Element firstParagraph = paragraphs.remove(0);

		for (int i = 0; i < festivalNames.size(); i++) {
			Element p = paragraphs.get(i);
			String festivalName = festivalNames.get(i).text();
			festivalName = festivalName.replaceAll("\\d{1,2}. ", "");
			String holdTime = "";
			String location = "";
			String doiTuongSuyTon = "";
			String desc = "";
			if (p != null) {
				String content = p.html();
				content = content.replace("<strong>", "");
				content = content.replace("</strong>", "");
				content = content.replace("&nbsp;", "");
				content = content.replace(":", "");
				content = content.replace("Địa điểm", "Địa điểm:");
				content = content.replace("Đối tượng suy tôn", "Đối tượng suy tôn:");
				content = content.replace("Thời gian", "Thời gian:");
				content = content.replace("Đặc điểm", "Đặc điểm:");
				content = content.trim();
				String[] data = content.split("<br>");
				for (String d : data) {
					d = d.trim();
					if (d.contains(":")) {
						String[] dataParts = d.split(":");
						String label = "";
						String contentString = "";
						label = label.concat(dataParts[0]);
						contentString = contentString.concat(dataParts[1]);
						contentString = contentString.trim();
//						System.out.println(label);
//						System.out.println(contentString);
						switch (label) {
						case "Thời gian": {
							String str = modify(label, contentString, holdTime);
							holdTime = holdTime.concat(str);
							break;
						}
						case "Địa điểm": {
							String str = modify(label, contentString, location);
							location = location.concat(str);
							break;
						}
						case "Đối tượng suy tôn": {
							String str = modify(label, contentString, doiTuongSuyTon);
							doiTuongSuyTon = doiTuongSuyTon.concat(str);
							break;
						}
						case "Đặc điểm": {
							String str = modify(label, contentString, desc);
							desc = desc.concat(str);
							break;
						}
						}
					} // end small if
				} // end small for
				Festival festival = new Festival(festivalName, holdTime, location, doiTuongSuyTon, desc);
				System.out.println("Crawl Festival (Tuyen Quang): " + festival.getName());
				this.addDataCrawl(festival);
			} // end if
		} // end for

	}

	public String modify(String label, String contentString, String str) {
		String modifiedString = str;
		modifiedString = modifiedString.concat(contentString);
		modifiedString = modifiedString.trim();
		return modifiedString;
	}

}
