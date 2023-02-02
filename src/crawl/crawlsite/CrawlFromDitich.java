package crawl.crawlsite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawl.Crawler;
import crawl.Crawling;
import model.HistoricalSite;

public class CrawlFromDitich extends Crawler<HistoricalSite> implements Crawling {
	private ArrayList<String> listDetailUrl = new ArrayList<>();

	public CrawlFromDitich(ArrayList<HistoricalSite> listDataCrawl) {
		// TODO Auto-generated constructor stub
		this.listDataCrawl=listDataCrawl;
	}

	@Override
	public void start() throws IOException {
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
}
