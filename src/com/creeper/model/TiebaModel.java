package com.creeper.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import com.creeper.util.MyTool;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tiebas")
public class TiebaModel implements Serializable {


	private static final long serialVersionUID = 1L;
	private int id;
	private String tieba_name;
	private String url;
	private List<TiebaInforModel> inforModels=new ArrayList<>();
	private String md3;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "increment")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length = 96,unique = true)
	public String getTieba_name() {
		return tieba_name;
	}
	public void setTieba_name(String tieba_name) {
		this.tieba_name = tieba_name;
		this.md3 = MyTool.md3(tieba_name);
	}
	public String getUrl() {
		return url;
	}
	@Column(length = 150)
	public void setUrl(String url) {
		this.url = url;
	}
	@OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
	@JoinColumn(name = "tiebaid")
	public List<TiebaInforModel> getInforModels() {
		return inforModels;
	}

	public void setInforModels(List<TiebaInforModel> inforModels) {
		this.inforModels = inforModels;
	}

	@Override
	public String toString() {
		return "标题=="+getTieba_name()+"\n" +
				"url=="+getUrl();
	}
	@Column(unique = true,length = 32)
	public String getMd3() {
		return md3;
	}
	public void setMd3(String md3) {
		this.md3 = md3;
	}
}