package com.creeper.model;

import org.hibernate.annotations.GenericGenerator;

import com.creeper.util.MyTool;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by 11723 on 2016/12/26.
 */
@Entity
@Table(name = "tiebaurls")
public class TiebaUrlModel implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private String url;
    private String md3;
    private boolean isGet;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "increment")
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
		this.md3 = MyTool.md3(url);
    }
    @Column(columnDefinition = "boolean default false")
    public boolean isIsGet() {
        return isGet;
    }

    public void setIsGet(boolean get) {
        isGet = get;
    }

	 @Column(unique = true,length = 32)
	public String getMd3() {
		return md3;
	}

	public void setMd3(String md3) {
		this.md3 = md3;
	}

    
}
