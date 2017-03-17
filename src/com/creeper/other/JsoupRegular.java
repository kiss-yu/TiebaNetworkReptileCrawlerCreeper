package com.creeper.other;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.sun.org.apache.xpath.internal.operations.Div;

public class JsoupRegular {

	private Document ROOTDOC;
	
	public List<String> getUrlList() {
		
		List<String> list=new ArrayList<>();
		for(int i = 0; i < ROOTDOC.select("a").size();i++){
			list.add(ROOTDOC.select("a").get(i).attr("abs:href"));
		}
		return deleteSame(list);
	} 
	
	public void start(String rootUrl) throws IOException {
//			ROOTDOC = Jsoup.connect(rootUrl).get();
		Connection con = Jsoup.connect(rootUrl);
		con.timeout(0);
		ROOTDOC=con.get();
	}
	
	private List<String> deleteSame(List<String> strings){
		for (int i = 0; i < strings.size(); i++) {
			for(int j= i+1;j < strings.size();j++){
				if (isSame(strings.get(i),strings.get(j).toString()) || isIleage(strings.get(j))) {
					strings.remove(j);
						j--;
				}
			}
		}
		return strings;
	}
	
	private boolean isSame(String url1,String url2){
		if (url1.equals(url2)) {
			return true;
		}if (url2.equals(url1+"#")) {
			return true;
		}if (url2.equals(url1+"/")) {
			return true;
		}if (url2.equals(url1+"//")) {
			return true;
		}
		return false;
	}
	
	private boolean isIleage(String string) {
		if (!string.matches("http.*")) {
			return true;
		}if (string.indexOf("//tieba") < 0) {
			return true;
		}if (string.length() < 22) {
			return true;
		}
		return false;
	}
	
	public String getTitle() {
		return ROOTDOC.title();
	}
	
	
	private Element getThreadList() {
		Element string=ROOTDOC.getElementById("thread_list");
		if (string != null) {
			return string;
		}
		return null;
	}
	
	private List<String> getLiSign(Element element){
		if (element == null) {
			return null;
		}
		List<String> strings=new ArrayList<>();
		for(int i = 0;i < element.select("div.threadlist_lz").select("div.clearfix").size() && element.select("div.threadlist_lz").select("div.clearfix").size() > 1;i ++){
			strings.add("第"+i+"帖子=="+element.select("div.threadlist_lz").select("div.clearfix").get(i).select("a").attr("title"));
		}
		return strings;
	}
	
	public List<String> getHotPostName() {
		return getLiSign(getThreadList());
	}
	
	
}
