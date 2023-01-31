package crawl.crawlfestival;

import java.io.IOException;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawl.Crawler;
import crawl.Crawling;
import model.Festival;

public class CrawlFestival extends Crawler<Festival> implements Crawling {

	public CrawlFestival() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start() throws IOException {
		crawlPhuThoFestival();
//		String url = "https://angiangtourist.vn/thoi-gian-va-dia-diem-to-chuc-cac-le-hoi-lon-o-an-giang/#";
//		this.getHTML(url);
//		this.doc.getElementById(url);
//		Element mainContent = this.doc.getElementsByClass("entry-content single-page").first();
//		Elements paragraphs = mainContent.getElementsByTag("p");
//		for (int i = 6; i <= 13; i++) {
//			Element p = paragraphs.get(i);
//			Element header = p.getElementsByTag("strong").first();
////			Festival festival = new Festival();
//			if (header != null) {
//				String content = p.html();
//				String nameFestival = header.text();
//				System.out.println(nameFestival);
//				int start = content.indexOf("<br>") + 4;
//				String mainData = content.substring(start);
//				String[] data = mainData.split("<br>");
////				System.out.println(data.length);
//				String holdTime = "";
//				String location = "";
//				String doiTuongSuyTon = "";
//				String desc = "";
//				for (String d : data) {
//					String[] dataParts = d.split(": ");
//					String label = "";
//					String contentString = "";
//					label = label.concat(dataParts[0]);
//					contentString = contentString.concat(dataParts[1]);
//					System.out.println(label);
//					System.out.println(contentString);
//					switch (label) {
//					case " Thời gian": {
//						holdTime = contentString;
//						holdTime = holdTime.trim();
//						break;
//					}
//					case " Địa điểm": {
//						location = contentString;
//						location = location.trim();
//						break;
//					}
//					case " Đối tượng suy tôn": {
//						if (contentString.contains("Trực")) {
//							int index = contentString.indexOf(",");
//							contentString = contentString.substring(0, index);
//						} else if (contentString.contains("Cảnh")) {
//							int index = contentString.indexOf("(");
//							contentString = contentString.substring(0, index);
//						}
//						doiTuongSuyTon = contentString;
//						doiTuongSuyTon = doiTuongSuyTon.trim();
//						break;
//					}
//					case " Đặc điểm": {
//						desc = contentString;
//						desc = desc.trim();
//						break;
//					}
//					}
//
//				}
//				Festival festival = new Festival(nameFestival, holdTime, location, doiTuongSuyTon, desc);
//				this.addDataCrawl(festival);
//
//			}
//
//		}
//		this.crawlBacNinhFestival();
//		String filePath = "D:\\learnHtml\\history_vietnam\\src\\crawl\\festival.json";
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//		try {
//			FileWriter writer = new FileWriter(new File(filePath));
//			gson.toJson(this.listDataCrawl, writer);
//			writer.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		;

	}

	public void crawlPhuThoFestival(){
		String url = "http://phutho.tintuc.vn/tin-tuc/tong-hop-cac-le-hoi-chinh-o-phu-tho.html";
		this.getHTML(url);
		Element mainContent = this.doc.getElementById("articleContent");
		Elements paragraphs = mainContent.select("p");
		for(Element paragraph: paragraphs) {
			if(paragraph.toString().contains("justify")) {
				System.out.println(paragraph);
			}
		}
	}

	public void crawlBacNinhFestival() {
		String url = "https://alltours.vn/bac-ninh/nhung-le-hoi-o-bac-ninh.html";
		this.getHTML(url);
		Element mainContent = this.doc.getElementsByClass("single-blog-content entry clr").first();
		Elements festivalNames = mainContent.select("h2");
		Elements paragraphs = mainContent.select("p:has(br)");
		for (int i=0;i<festivalNames.size();i++) {
			Element p = paragraphs.get(i);
			String festivalName = festivalNames.get(i).text();
			festivalName = festivalName.replaceAll("\\d{1,2}. ", "");
			System.out.println(festivalName);
			String holdTime = "";
			String location = "";
			String doiTuongSuyTon ="";
			String desc = "";
			if (p != null) {
				String content = p.html();
				content = content.replace("hoàng:", "hoàng");
				String [] data = content.split("<br>");
				for (String d:data) {
					System.out.println(d);
					String [] dataParts = d.split(": ");
					String label ="";
					String contentString = "";
					label = label.concat(dataParts[0]);
					contentString = contentString.concat(dataParts[1]);
					System.out.println(label);
					System.out.println(contentString);
					switch(label){
					case "Thời gian":{
						holdTime = holdTime.concat(contentString);
						holdTime = holdTime.trim();
						break;
					}
					case " Địa điểm":{
						location = location.concat(contentString);
						location = location.trim();
						break;
					}
					case " Đối tượng suy tôn":{
						if (contentString.contains("Truyền")) {
							int index = contentString.indexOf("Nguyễn");
							contentString = contentString.substring(index);
						}
						doiTuongSuyTon = doiTuongSuyTon.concat(contentString);
						doiTuongSuyTon =  doiTuongSuyTon.trim();
						break;
					}
					case " Đặc điểm":{
						desc = desc.concat(contentString);
						desc = desc.trim();
						break;
					}
					}
				}
			}
			Festival festival= new Festival(festivalName, holdTime, location, doiTuongSuyTon, desc);
			this.addDataCrawl(festival);
		}

	}

	public static void main(String[] args) {
		CrawlFestival des = new CrawlFestival();
		try {
			des.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
