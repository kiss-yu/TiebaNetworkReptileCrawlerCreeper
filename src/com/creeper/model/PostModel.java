package com.creeper.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import com.creeper.util.MyTool;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "posts")
public class PostModel implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String posttitle;
	private String createAuthor;
	private String url;
	private Date endtime;
	private TiebaInforModel inforModel;
	private String tiebaName;
	private Integer inforid;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid",strategy = "increment")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(length = 500)
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
	@Column(length = 100)
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
	@ManyToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
	@JoinColumn(name = "inforid",updatable=false,insertable=false)
	public TiebaInforModel getInforModel() {
		return inforModel;
	}
	public void setInforModel(TiebaInforModel inforModel) {
		this.inforModel = inforModel;
	}

	public String getTiebaName() {
		return tiebaName;
	}

	public void setTiebaName(String tiebaName) {
		this.tiebaName = tiebaName;
	}


	public Integer getInforid() {
		return inforid;
	}
	public void setInforid(Integer inforid) {
		this.inforid = inforid;
	}
	
	@Override
	public String toString() {
		return  "\n"+"帖子名=="+getPosttitle()+"\n"
				+ "作者=="+getCreateAuthor()+"\n"
						+ "时间=="+MyTool.getGoodTime(getEndtime())+"\n"
										+ "地址=="+getUrl();
	}

}
