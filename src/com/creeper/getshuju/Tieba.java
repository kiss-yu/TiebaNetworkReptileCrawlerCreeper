package com.creeper.getshuju;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.creeper.model.TiebaModel;

public class Tieba extends TopPareant{

	private TiebaModel tiebaModel=new TiebaModel();

	public Tieba(String url){
		tiebaModel.setUrl(url);
		setTitle();
		setPostList();
	}
	
	
	private void setPostList() {
		tiebaModel.setPostLists(new Posts().getPostList());
	}
	
	private void setTitle() {
		setCreateTime();
		String title=ROOTDOC.title();
		try {
			tiebaModel.setTieba_name(getTitleOne(title));
		} catch (Exception e) {
			try {
				tiebaModel.setTieba_name(getTitleTwo(title));
			} catch (Exception e1) {
				tiebaModel.setTieba_name(getTitleThree(title));
			}
		}
		
	}
	private String getTitleTwo(String title) throws Exception{
		if (title.length() < 3 || !title.matches(".*吧.*")) {
			throw new Exception();
		}
		return title;
	}
	
	private String getTitleThree(String title){
		try {
			return ROOTDOC.getElementsByClass("header").first()
					.getElementById("pagelet_frs-header/pagelet/head")
					.select("a.pagelet_frs-header/pagelet/head").text();
		} catch (Exception e) {
			return null;
		}
	}
	
	
	
	
	private String getTitleOne(String title) throws Exception{
		try {
			String[] str=title.split("_");
			return str[str.length-2];
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	
	
	private void setCreateTime() {
		setUserNumber();
		tiebaModel.setCreate_time(new Date((new GregorianCalendar().getTimeInMillis())));
	}

	private void setUserNumber() {
		try {
			String header=getHeader();
			tiebaModel.setTieba_user_number(getNumber(header, "关注"));
			setPostNumber(header);
		} catch (Exception e) {
			tiebaModel.setTieba_user_number(0);
		}
	}
	
	private void setPostNumber(String header){
		try {
			tiebaModel.setPost_number(getNumber(header, "贴子"));	
		} catch (Exception e) {
			tiebaModel.setPost_number(0);	
		}
	}
		
	private String getHeader(){
		return ROOTDOC.getElementsByClass("header").first().toString();
	}
	
	private Integer getNumber(String content,String key){
		try {
				return getNum(content.split(key)[1]);
		} catch (Exception e) {
		}
		return 0;
	}
	
	
	
	private Integer getNum(String string){
		try {
			Pattern p = Pattern.compile("[[\\d]+[,|-]{1}]+[\\d]+");
			Matcher m = p.matcher(string);
			while(m.find()){
			   return Integer.valueOf(m.group().replaceAll("[\\D]", ""));  
		  }
		} catch (Exception e) {
		}
		return 0;
	}
	
	
	public TiebaModel getTiebaModel() {
		return tiebaModel;
	}
	
	
	
}
