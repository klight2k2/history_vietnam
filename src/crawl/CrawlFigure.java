package crawl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import history.HistoricalFigure;

public class CrawlFigure extends Crawler<HistoricalFigure> implements Crawling {
	private ArrayList<HistoricalFigure> listFigure = new ArrayList<>();

	public CrawlFigure() {
		// TODO Auto-generated constructor stub
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
		System.out.println(content);
		String data = content.substring(content.indexOf("\"events\":") + 9, content.indexOf("},{\"script_path\""));
		try {
			JSONArray list = new JSONArray(data);
			for (int i = 0; i < list.length(); i++) {
				JSONObject obj = list.getJSONObject(i);
				obj.getJSONObject("start_date");
				String nameFigure;
				String born = "";
				String died = "";
				String bornIn = "";
				nameFigure = obj.getJSONObject("text").getString("headline");
				if (obj.has("start_date")) {

					born = obj.getJSONObject("start_date").getString("year");
				}
				if (obj.has("end_date")) {

					died = obj.getJSONObject("end_date").getString("year");
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
				} else if(lengthListMoreInfo>0) {
					otherName = listMoreInfo.get(0).getElementsByTag("td").get(0).text();
					bornIn = listMoreInfo.get(0).getElementsByTag("td").get(2).text();
				};
				System.out.println(otherName + " " + bornIn);

				HistoricalFigure figure = new HistoricalFigure(nameFigure, died, born, bornIn);
				figure.addOtherName(otherName);
				System.out.println(bornIn);
				this.addDataCrawl(figure);

			}
			String filePath = "D:\\oop2\\OOP\\vietnamHistory\\src\\crawl\\figure.json";
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			try {
				FileWriter writer = new FileWriter(new File(filePath));
				gson.toJson(this.listDataCrawl, writer);
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		CrawlFigure des = new CrawlFigure();
		try {
			des.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
