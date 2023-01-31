package crawl.crawlsite;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import crawl.Crawler;
import crawl.Crawling;
import model.HistoricalFigure;
import model.HistoricalSite;

public class CrawlSite extends Crawler<HistoricalSite> implements Crawling {
	private ArrayList<String> listDetailUrl = new ArrayList<>();

	public CrawlSite() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start() throws IOException {
		this.crawlFromDitich();
		this.crawlWiki();
		String filePath = "D:\\learnHtml\\history_vietnam\\src\\crawl\\site.json";
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

	public void crawlFromDitich() {
		// TODO Auto-generated method stub
		String baseUrl = "http://ditich.vn";
		for (int i = 1; i < 64; i++) {
			this.getHTML(baseUrl + "/FrontEnd/DiTich?cpage=" + i + "&rpage=64");
			Elements listSite = this.doc.getElementsByClass("ih-item square colored effect4");
			for (Element site : listSite) {
				listDetailUrl.add(site.getElementsByTag("a").attr("href"));

			}
		}
//		listDetailUrl.add("/FrontEnd/DiTich/Form?do=&ItemId=2022");
		for (int i = 0; i < listDetailUrl.size(); i++) {
			this.getHTML(baseUrl + listDetailUrl.get(i));
			Element info = this.doc.getElementsByClass("hl__library-info__container").get(0);
			Elements listInput = info.getElementsByTag("input");
			String nameSite = "";
			String location = "";
			String objectWorship = "";
			String loaiHinhXepHang = "";
			String loaiXepHang = "";
			String builtIn = "";
//			String doiTuongTho="Đối tượng thờ";
			Elements moreInfo = this.doc.select("div.hl__illustrated-list__list-item");
			for (Element data : moreInfo) {
				if (data.text().contains("Đối tượng thờ")) {

					objectWorship = data.text().substring(("Đối tượng thờ").length());
					System.out.println(objectWorship);
				}
				if (data.text().contains("Niên đại khởi dựng")) {
					builtIn = data.text().substring(("Niên đại khởi dựng").length());
					System.out.println(builtIn);
				}
			}
			for (Element input : listInput) {
				String id = input.id();

				String value = input.attr("value");
				if (value.equals("--- Lựa chọn ---")) {
					value = "";
				}
				switch (id) {
				case "tenId":
					nameSite = value;
					break;
				case "diaDanhId":
					location = value;
					break;
				case "loaiHinhXepHangId":
					loaiHinhXepHang = value;
					break;
				case "loaiXepHangId":
					loaiXepHang = value;
					break;

				default:
					break;
				}
			}
			HistoricalSite site = new HistoricalSite(nameSite, builtIn, location, objectWorship, loaiXepHang,
					loaiHinhXepHang);
			this.addDataCrawl(site);

		}
	}
	
	public void crawlWiki() {
		String url = "https://wiki.edu.vn/wiki/index.php?title=Di_t%C3%ADch_l%E1%BB%8Bch_s%E1%BB%AD_v%C4%83n_h%C3%B3a_qu%E1%BB%91c_gia";
		this.getHTML(url);
		Elements listTableSite = this.doc.select("table ");
		int sizeListTable = listTableSite.size();
		for (int i = 0; i < sizeListTable; i++) {
			Elements listData = listTableSite.get(i).select("tbody > tr");
			listData.remove(0);
			for (Element data : listData) {
				String builtIn = "";
				String loaiXepHang = "";
				String loaiHinhXepHang = "";
				String objectWorship = "";
				String location = "";
				String nameSite = "";
				if (data.select("td").size() > 5) {

					nameSite = data.select("td").get(0).text().trim();
					location = data.select("td").get(1).text().trim();
					objectWorship = "";
					loaiHinhXepHang = data.select("td").get(2).text().trim();
					loaiXepHang = "di tích cấp quốc gia";

				} else {
					nameSite = data.select("td").get(1).text().trim();
					location = data.select("td").get(2).text().trim();
					loaiHinhXepHang = data.select("td").get(3).text().trim();
					loaiXepHang = "di tích cấp quốc gia";
				}
				HistoricalSite site= new HistoricalSite(nameSite, builtIn, location, objectWorship, loaiXepHang, loaiHinhXepHang);
				this.addDataCrawl(site);
			}

		}
	}

	public static void main(String[] args) {
		CrawlSite des = new CrawlSite();
		try {
			des.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
