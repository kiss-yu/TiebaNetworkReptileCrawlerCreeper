package com.creeper.model;

import com.creeper.model.AllUrlModel;
import com.creeper.util.MyTool;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "childurls")
public class ChildUrlModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String url;
	private boolean isGet;
	private AllUrlModel allUrlModel;
	private String md3;

	@Id
	@Column(name = "id",unique = true,length = 11)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "increment")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(length = 1000,unique = true)
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
		this.md3 = MyTool.md3(url);
	}
	@Column(columnDefinition = "boolean default false")
	public boolean isIsGet() {
		return isGet;
	}
	public void setIsGet(boolean isGet) {
		this.isGet = isGet;
	}
	@ManyToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	@JoinColumn(name = "pareantId")
	public AllUrlModel getAllUrlModel() {
		return allUrlModel;
	}

	public void setAllUrlModel(AllUrlModel allUrlModel) {
		this.allUrlModel = allUrlModel;
	}
	@Override
	public String toString() {
		return "Url=="+getUrl()+"\n"
				+ "是不是请求过=="+isIsGet();
	}
	
	@Column(unique = true,length = 32)
	public String getMd3() {
		return md3;
	}
	public void setMd3(String md3) {
		this.md3 = md3;
	}
}
