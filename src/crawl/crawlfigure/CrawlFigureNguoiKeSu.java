package crawl.crawlfigure;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import crawl.Crawler;
import crawl.Crawling;
import model.HistoricalFigure;

public class CrawlFigureNguoiKeSu extends Crawler<HistoricalFigure> implements Crawling {
	public CrawlFigureNguoiKeSu(ArrayList<HistoricalFigure> listDataCrawl) {
		this.listDataCrawl = listDataCrawl;
	}

	private static String encodeValue(String value) {
		try {
			return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException ex) {
			throw new RuntimeException(ex.getCause());
		}
	}

	@Override
	public void start() throws IOException {
		// TODO Auto-generated method stub
		String url = "https://nguoikesu.com/media/com_jchoptimize/cache/js/fc4bb2d9dd2285de862fb820400c7f0a1db36a28cda384580ca4c8147ae3f67f.js";
//		this.getHTML(detailUrl);
//		Elements listMoreInfo = this.doc.select("table.selectable > tbody > tr");
//		int lengthListMoreInfo = listMoreInfo.size();
//		String otherName = "";
//		if (listMoreInfo.text().contains(nameFigure)) {
//			for (Element moreInfo : listMoreInfo) {
//				if (moreInfo.text().contains(nameFigure)) {
//					bornIn = moreInfo.getElementsByTag("td").get(2).text();
//				}
//			}
//		} else {
//			otherName = listMoreInfo.get(0).getElementsByTag("td").get(0).text();
//			bornIn = listMoreInfo.get(0).getElementsByTag("td").get(2).text();
//		};
//	System.out.println(otherName+" "+bornIn);

		this.get(url);
		String content = (String) response.body();
		String data = content.substring(content.indexOf("\"events\":") + 9, content.indexOf("},{\"script_path\""));
		try {
			JSONArray list = new JSONArray(data);
			for (int i = 0; i < list.length(); i++) {
//			for (int i = 0; i < 10; i++) {
				JSONObject obj = list.getJSONObject(i);
				obj.getJSONObject("start_date");
				String nameFigure = obj.getJSONObject("text").getString("headline");
				String desc = obj.getJSONObject("text").getString("text");
				String born = "";
				String died = "";
				String bornIn = "";
				String imageLink = "";
				if (obj.has("start_date")) {
					born = obj.getJSONObject("start_date").getString("year");
				}
				if (obj.has("end_date")) {

					died = obj.getJSONObject("end_date").getString("year");
				}
				if (obj.has("media")) {

					imageLink ="https://nguoikesu.com" +  obj.getJSONObject("media").getString("thumbnail");
				}
				String detailUrl = "https://vansu.vn/viet-nam/viet-nam-nhan-vat?keyword=" + encodeValue(nameFigure);
				this.getHTML(detailUrl);
				Elements listMoreInfo = this.doc.select("table.selectable > tbody > tr");
				int lengthListMoreInfo = listMoreInfo.size();
				String otherName = "";
				if (listMoreInfo.text().contains(nameFigure)) {
					for (Element moreInfo : listMoreInfo) {
						if (moreInfo.text().contains(nameFigure)) {
							bornIn = moreInfo.getElementsByTag("td").get(2).text();
						}
					}
				} else if (lengthListMoreInfo > 0) {
					otherName = listMoreInfo.get(0).getElementsByTag("td").get(0).text();
					bornIn = listMoreInfo.get(0).getElementsByTag("td").get(2).text();
				}

				HistoricalFigure figure = new HistoricalFigure(nameFigure, died, born, bornIn, desc, imageLink);
				figure.addOtherName(otherName);
				System.out.println("Crawl Figure: " + figure.getName());
				this.addDataCrawl(figure);

			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
