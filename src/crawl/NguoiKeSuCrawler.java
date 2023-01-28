package crawl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Scanner;

import org.jsoup.Jsoup;

import org.json.simple.JSONArray;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

public class NguoiKeSuCrawler {

	public NguoiKeSuCrawler() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {
		// The URL of the page you want to crawl
//		String urlD = "https://nguoikesu.com/media/com_jchoptimize/cache/js/fc4bb2d9dd2285de862fb820400c7f0a85af6dd1192c206f119eb09fb28c2fd3.js";
//		System.out.println("hello");
//		URL url = new URL(urlD);
//		HttpURLConnection con = (HttpURLConnection) url.openConnection();
//		con.setRequestMethod("GET");
//		// Connect to the website and get the HTML
////		Document doc = Jsoup.connect(url).get();
//		con.setDoOutput(true);
//		int status = con.getResponseCode();
//		BufferedReader in = new BufferedReader(
//				  new InputStreamReader(con.getInputStream()));
//				String inputLine;
//				StringBuffer content = new StringBuffer();
//				while ((inputLine = in.readLine()) != null) {
//				    content.append(inputLine);
//				}
//				in.close();
//		System.out.println("test"+in);
		// create a client
		var client = HttpClient.newHttpClient();

		// create a request
		var request = HttpRequest.newBuilder(
		       URI.create("https://nguoikesu.com/media/com_jchoptimize/cache/js/fc4bb2d9dd2285de862fb820400c7f0a85af6dd1192c206f119eb09fb28c2fd3.js"))
		   .header("accept", "application/json")
		   .build();

		// use the client to send the request
		HttpResponse<String> response;
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

		// the response:
		// Find all the a elements
//		Elements elementsLevel0 = doc
//				.select("#jm-left > div > div:nth-child(1) > div > div.jm-module-content.clearfix > ul > li > a");
//		Elements elementsLevel1 = doc.select(
//				"#jm-left > div > div:nth-child(1) > div > div.jm-module-content.clearfix > ul > li > ul > li > a");
//		elementsLevel0.addAll(elementsLevel1);
//		Elements elements = elementsLevel0;
//
//		// Create a StringBuilder to store the href values
//		StringBuilder hrefs = new StringBuilder();
//
//		// Loop through each a element and get the href value
//		for (Element element : elements) {
//			String href = url + element.attr("href");
//			hrefs.append(href).append("\n");
//		}
//		System.out.println(hrefs.toString());

		// Write the href values to a text file
//		try (FileWriter file = new FileWriter("hrefs.txt")) {
//			file.write(hrefs.toString());
//			file.flush();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

}
