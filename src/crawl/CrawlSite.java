package crawl;

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

import history.HistoricalFigure;
import history.HistoricalSite;

public class CrawlSite extends Crawler implements Crawling {
	private ArrayList<String> listDetailUrl = new ArrayList<>();
	private ArrayList<HistoricalSite> listSite= new ArrayList<>();
	public CrawlSite() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start() throws IOException {
		// TODO Auto-generated method stub
		String baseUrl = "http://ditich.vn";
		for (int i = 1; i < 2; i++) {
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
			String builtIn="";
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
				if(value.equals("--- Lựa chọn ---")) {
					value="";
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
			HistoricalSite site= new HistoricalSite(nameSite,builtIn, location, objectWorship, loaiXepHang, loaiHinhXepHang);
			listSite.add(site);
			

		}
		String filePath = "D:\\oop2\\OOP\\vietnamHistory\\src\\crawl\\site.json";
		  BufferedImage image = ImageIO.read(new URL("http://ditich.vn/upload/images/ditich/Ch-L0009.jpg"));
		  ImageIO.write(image , "png", new File("D:\\oop2\\OOP\\vietnamHistory\\data\\image.png"));
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try {
			FileWriter writer = new FileWriter(new File(filePath));
			gson.toJson(listSite, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		System.out.println(listDetailUrl);

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
