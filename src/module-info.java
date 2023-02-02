module vietnamHistory {
	exports controller;
	exports view;
	exports crawl.crawlfestival;
	exports main;
	exports model;
	exports crawl;

	requires gson;
	requires java.desktop;
	requires java.net.http;
	requires javafx.base;
	requires javafx.fxml;
	requires javafx.graphics;
	requires json;
	requires json.simple;
	requires org.jsoup;
	requires javafx.controls;
	requires java.sql;
	opens crawl.crawlera to gson;
	opens model to gson;
	opens view to javafx.fxml;
	
}