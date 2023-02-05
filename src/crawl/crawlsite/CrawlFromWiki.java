package crawl.crawlsite;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawl.Crawler;
import crawl.Crawling;
import model.HistoricalSite;

public class CrawlFromWiki extends Crawler<HistoricalSite> implements Crawling {

	public CrawlFromWiki(ArrayList<HistoricalSite> listDataCrawl) {
		// TODO Auto-generated constructor stub
		this.listDataCrawl = listDataCrawl;
	}

	@Override
	public void start() throws IOException {
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
				String imageLink = "";
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
				HistoricalSite site = new HistoricalSite(nameSite, builtIn, location, objectWorship, loaiXepHang,
						loaiHinhXepHang, imageLink);
				System.out.println(
						"Crawl Site (Wiki): " + site.getId() + "::" + site.getName() + ", Worship: " + objectWorship);
				this.addDataCrawl(site);
			}
		}

	}

}
