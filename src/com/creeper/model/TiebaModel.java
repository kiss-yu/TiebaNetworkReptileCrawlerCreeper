package com.creeper.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.creeper.util.MyTool;

public class TiebaModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String tieba_name;
	private Integer tieba_user_number;
	private Date create_time;
	private Integer post_number;
	private String url;
	private List<PostModel> postLists=new ArrayList<PostModel>();
public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTieba_name() {
		return tieba_name;
	}
	public void setTieba_name(String tieba_name) {
		this.tieba_name = tieba_name;
	}
	public Integer getTieba_user_number() {
		return tieba_user_number;
	}
	public void setTieba_user_number(Integer tieba_user_number) {
		this.tieba_user_number = tieba_user_number;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getPost_number() {
		return post_number;
	}
	public void setPost_number(Integer post_number) {
		this.post_number = post_number;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public  List<PostModel> getPostLists() {
		return postLists;
	}
	public void setPostLists( List<PostModel> postLists) {
		this.postLists = postLists;
	}
	
	@Override
	public String toString() {
		return "标题=="+getTieba_name()+"\n"
				+ "关注人数=="+getTieba_user_number()+"\n"
						+ "帖子数=="+getPost_number()+"\n"
//								+ "创建时间"+new Date(getCreate_time()).toString();
						+ "创建时间"+MyTool.getGoodTime(getCreate_time());
	}
}
