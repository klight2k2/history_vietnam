package crawl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class Crawler<T>{
	protected Document doc;
	protected HttpResponse<String> response;
	protected ArrayList<T> listDataCrawl;
	public Crawler() {
		// TODO Auto-generated constructor stub
	}
	
	
	public void getHTML(String url) {
		try {
			doc = Jsoup.connect(url).userAgent("Mozilla").get();
		} catch (IOException e) {
			System.out.println("Error from url:" +url);
			e.printStackTrace();
		}
	}

	public void get(String url) {
		var client = HttpClient.newHttpClient();

		// create a request
		var request = HttpRequest.newBuilder(URI.create(url)).header("accept", "application/json").build();

		// use the client to send the request

		try {
			response = client.send(request, BodyHandlers.ofString());
			System.out.println(response.body());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public boolean addDataCrawl(T data) {
		if(listDataCrawl.contains(data)) {
			return false;
		}else {
			listDataCrawl.add(data);
			return true;
		}
	}

}
