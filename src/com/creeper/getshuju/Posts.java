package com.creeper.getshuju;


import java.util.*;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.creeper.model.PostModel;

public class Posts extends TopPareant {

	List<PostModel> models=new ArrayList<>();
	private int sort;
	private String tiebaName;
	public Posts(String tiebaName){
		this.tiebaName=tiebaName;
	}
	private Elements getLiSign(Element element){
		if (element == null) {
			return null;
		}
		Elements lis=element.select("div.t_con").select("div.cleafix");
		return lis;
	}

	private Element getThreadList() {
		Element ul=ROOTDOC.getElementById("thread_list");
		if (ul != null) {
			return ul;
		}
		return null;
	}

	public List<PostModel> getPostList(){
		try {
			Elements elements=getLiSign(getThreadList());
			int size = elements.size();
			for(sort= 0;sort < size ;sort ++){
				PostModel postModel=loadPostModel(elements.get(sort));
				if (!isNullPost(postModel)) {
					models.add(postModel);
				}else {
					size--;
				}
			}
		} catch (Exception e) {

		}
		return models;
	}


	private boolean isNullPost(PostModel postModel){
		if (postModel.getPosttitle() == null) {
			return true;
		}if (postModel.getUrl() == null) {
			return true;
		}
		return false;
	}

	private PostModel loadPostModel(Element element){
		PostModel postModel=new PostModel();
		try {
			postModel.setTiebaName(tiebaName);
			postModel.setPosttitle(setPostTitle(element));
			postModel.setCreateAuthor(setCreateAuthor(element));
			postModel.setUrl(setUrl(element));
			postModel.setEndtime(setEndtime(element));
		} catch (Exception e) {
		}
		return postModel;
	}

	private int setSort(){
		return (sort+1);
	}

	private String setPostTitle(Element element){
		return element.select("div.threadlist_lz").select("div.clearfix").select("a").first().attr("title");
	}

	private String setCreateAuthor(Element element){
		return element.select("div.threadlist_author").select("div.pull_right").select("span.frs-author-name-wrap").select("a").text();
	}

	private String setUrl(Element element){
		return element.select("div.threadlist_lz").select("div.clearfix").select("a").first().attr("abs:href");
	}

	private Date setEndtime(Element element){
		return new Date(transitionTime(element.select("div.threadlist_author").select("div.pull_right").select("span.threadlist_reply_date").
				select("span.pull_right").select("span.j_reply_data").text()));
	}

	private long transitionTime(String time) {
		try {
			if (time.matches(".*:.*")) {
				return getLongTimeOne(time);
			}else{
				return getLongTimeTwo(time);
			}
		} catch (Exception e) {
			return new GregorianCalendar().getTimeInMillis();
		}

	}

	private long getLongTimeOne(String time){
		Calendar calendar=new GregorianCalendar();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH), Integer.valueOf(time.split(":")[0]),
				Integer.valueOf(time.split(":")[1]),0);
		return calendar.getTimeInMillis();
	}
	private long getLongTimeTwo(String time){
		Calendar calendar=new GregorianCalendar();
		calendar.set(calendar.get(Calendar.YEAR),Integer.valueOf(time.split("-")[0])-1,
				Integer.valueOf(time.split("-")[1]),0,0,0);
		if (calendar.getTimeInMillis() > new GregorianCalendar().getTimeInMillis()) {
			calendar.set(calendar.get(Calendar.YEAR)-1, Integer.valueOf(time.split("-")[0])-1, Integer.valueOf(time.split("-")[1]));
		}
		return calendar.getTimeInMillis();
	}

}
