package crawl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import history.HistoricalFigure;



public class CrawlFigure extends Crawler implements Crawling {
	private ArrayList<HistoricalFigure> listFigure=new ArrayList<>();
	public CrawlFigure() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start() throws IOException {
		// TODO Auto-generated method stub
		String url = "https://nguoikesu.com/media/com_jchoptimize/cache/js/fc4bb2d9dd2285de862fb820400c7f0a1db36a28cda384580ca4c8147ae3f67f.js";
		this.get(url);

		String content = response.body();
		String data = content.substring(content.indexOf("\"events\":")+9, content.indexOf("},{\"script_path\""));
		try {
			JSONArray list = new JSONArray(data);
			for (int i = 0 ; i < list.length(); i++) {
		        JSONObject obj = list.getJSONObject(i);
		        obj.getJSONObject("start_date");
		        String nameFigure;
		        String born="";
		        String died="";
		        nameFigure=obj.getJSONObject("text").getString("headline");
		        if(obj.has("start_date")) {
		        	
		        born=obj.getJSONObject("start_date").getString("year");
		        }
		        if(obj.has("end_date")) {
		        	
		        died=obj.getJSONObject("end_date").getString("year");
		        }
		        HistoricalFigure figure=new HistoricalFigure(nameFigure, died, born);
		        listFigure.add(figure);
		      
		        System.out.println( obj.getJSONObject("start_date"));
		    }
			String filePath = "D:\\oop2\\OOP\\vietnamHistory\\src\\crawl\\figure.json";
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			try {
				FileWriter writer = new FileWriter(new File(filePath));
				gson.toJson(listFigure, writer);
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
