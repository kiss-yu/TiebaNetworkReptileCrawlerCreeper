package com.creeper.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by 11723 on 2016/12/29.
 */
@Entity
@Table(name = "tiebainfor")
public class TiebaInforModel {
    private int id;
    private Date createTime;
    private Integer tieba_user_number;
    private Integer post_number;
    private TiebaModel tiebaModel;
    private String tiebaName;
    private List<PostModel> postModels=new ArrayList<>();
    private Integer tiebaid;
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "increment")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getTieba_user_number() {
        return tieba_user_number;
    }

    public void setTieba_user_number(int tieba_user_number) {
        this.tieba_user_number = tieba_user_number;
    }

    public int getPost_number() {
        return post_number;
    }

    public void setPost_number(int post_number) {
        this.post_number = post_number;
    }
    @ManyToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinColumn(name = "tiebaid",updatable=false,insertable=false)
    public TiebaModel getTiebaModel() {
        return tiebaModel;
    }

    public void setTiebaModel(TiebaModel tiebaModel) {
        this.tiebaModel = tiebaModel;
    }
    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinColumn(name = "inforid")
    public List<PostModel> getPostModels() {
        return postModels;
    }

    public void setPostModels(List<PostModel> postModels) {
        this.postModels = postModels;
    }
    @Column(length = 150)
    public String getTiebaName() {
        return tiebaName;
    }

    public void setTiebaName(String tiebaName) {
        this.tiebaName = tiebaName;
    }

	public Integer getTiebaid() {
		return tiebaid;
	}

	public void setTiebaid(Integer tiebaid) {
		this.tiebaid = tiebaid;
	}

	public void setTieba_user_number(Integer tieba_user_number) {
		this.tieba_user_number = tieba_user_number;
	}

	public void setPost_number(Integer post_number) {
		this.post_number = post_number;
	}
    @Override
    public String toString() {
    	return "postNumber==" + getPost_number();
    }
}
