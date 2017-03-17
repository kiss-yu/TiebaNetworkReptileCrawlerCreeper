package com.creeper.model;

import java.io.Serializable;

public class ChildUrlModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String url;
	private boolean isGet;
	private Integer pareantId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isGet() {
		return isGet;
	}
	public void setGet(boolean isGet) {
		this.isGet = isGet;
	}
	public Integer getPareantId() {
		return pareantId;
	}
	public void setPareantId(Integer pareantId) {
		this.pareantId = pareantId;
	}
	@Override
	public String toString() {
		return "Url=="+getUrl()+"\n"
				+ "是不是请求过=="+isGet();
	}
}
