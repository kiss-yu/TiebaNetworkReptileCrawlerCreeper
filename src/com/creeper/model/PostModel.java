package com.creeper.model;

import java.io.Serializable;
import java.util.Date;

import com.creeper.util.MyTool;


public class PostModel implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Integer sort;
	private String posttitle;
	private String createAuthor;
	private String url;
	private Date endtime;
	private Integer tiebaid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getPosttitle() {
		return posttitle;
	}
	public void setPosttitle(String posttitle) {
		this.posttitle = posttitle;
	}
	public String getCreateAuthor() {
		return createAuthor;
	}
	public void setCreateAuthor(String createAuthor) {
		this.createAuthor = createAuthor;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public Integer getTiebaid() {
		return tiebaid;
	}
	public void setTiebaid(Integer tiebaid) {
		this.tiebaid = tiebaid;
	}
	

	
	@Override
	public String toString() {
		return  "\n"+"帖子名=="+getPosttitle()+"\n"
				+ "作者=="+getCreateAuthor()+"\n"
						+ "时间=="+MyTool.getGoodTime(getEndtime())+"\n"
								+ "排名=="+getSort()+"\n"
										+ "地址=="+getUrl();
	}
	
}
