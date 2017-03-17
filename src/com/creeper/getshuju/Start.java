package com.creeper.getshuju;

import java.io.IOException;
import java.io.Reader;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class Start {

	public  void startThread(String root) throws IOException {
		Connection con = Jsoup.connect(root);
		con.timeout(0);
		Url.setROOTDOC(con.get());
	}

	public  void start(String root) throws IOException {
		Connection con = Jsoup.connect(root);
		con.timeout(0);
		TopPareant.setROOTDOC(con.get());
	}
	
	public  String startGetPost(String root) throws IOException {
		Connection con = Jsoup.connect(root);
		return con.get().toString();
	}
	
}
