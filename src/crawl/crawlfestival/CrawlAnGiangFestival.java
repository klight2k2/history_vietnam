package crawl.crawlfestival;

import java.io.IOException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawl.Crawler;
import crawl.Crawling;
import model.Festival;

public class CrawlAnGiangFestival extends Crawler<Festival> implements Crawling {

	public CrawlAnGiangFestival() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start() throws IOException {
		String url = "https://angiangtourist.vn/thoi-gian-va-dia-diem-to-chuc-cac-le-hoi-lon-o-an-giang/#";
		this.getHTML(url);
		this.doc.getElementById(url);
		Element mainContent = this.doc.getElementsByClass("entry-content single-page").first();
		Elements paragraphs = mainContent.getElementsByTag("p");
		for (int i = 6; i <= 13; i++) {
			Element p = paragraphs.get(i);
			Element header = p.getElementsByTag("strong").first();
//			Festival festival = new Festival();
			if (header != null) {
				String content = p.html();
				String nameFestival = header.text();
				System.out.println(nameFestival);
				int start = content.indexOf("<br>") + 4;
				String mainData = content.substring(start);
				String[] data = mainData.split("<br>");
//				System.out.println(data.length);
				String holdTime = "";
				String location = "";
				String doiTuongSuyTon = "";
				String desc = "";
				for (String d : data) {
					String[] dataParts = d.split(": ");
					String label = "";
					String contentString = "";
					label = label.concat(dataParts[0]);
					contentString = contentString.concat(dataParts[1]);
					System.out.println(label);
					System.out.println(contentString);
					switch (label) {
					case " Thời gian": {
						holdTime = contentString;
						holdTime = holdTime.trim();
						break;
					}
					case " Địa điểm": {
						location = contentString;
						location = location.trim();
						break;
					}
					case " Đối tượng suy tôn": {
						if (contentString.contains("Trực")) {
							int index = contentString.indexOf(",");
							contentString = contentString.substring(0, index);
						} else if (contentString.contains("Cảnh")) {
							int index = contentString.indexOf("(");
							contentString = contentString.substring(0, index);
						}
						doiTuongSuyTon = contentString;
						doiTuongSuyTon = doiTuongSuyTon.trim();
						break;
					}
					case " Đặc điểm": {
						desc = contentString;
						desc = desc.trim();
						break;
					}
					}

				}
				Festival festival = new Festival(nameFestival, holdTime, location, doiTuongSuyTon, desc);
				this.addDataCrawl(festival);

			}

		}
//		this.crawlBacNinhFestival();
		String filePath = "D:\\learnHtml\\history_vietnam\\src\\crawl\\festival.json";
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
	

}
