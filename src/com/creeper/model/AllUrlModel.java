package com.creeper.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

import com.creeper.hibernate.util.HibernateUtil;
import com.creeper.util.MyTool;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 11723 on 2016/12/26.
 */
@Entity
@Table(name = "allurls")
public class AllUrlModel implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private String url;
    private boolean childFinish;
    private boolean isHaveChild;
    private List<ChildUrlModel> childUrlModels=new ArrayList<>();
    private String md3; 
    @Id
    @Column(unique = true,length = 11)
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
    public boolean isChildFinish() {
        return childFinish;
    }

    public void setChildFinish(boolean childFinish) {
        this.childFinish = childFinish;
    }
    @Column(columnDefinition = "boolean default true")
    public boolean isIsHaveChild() {
        return isHaveChild;
    }

    public void setIsHaveChild(boolean isHaveChild) {
        this.isHaveChild = isHaveChild;
    }

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinColumn(name = "pareantId")
//    @Index(name = "tiebas_Test_allurls_childurls", columnNames = "childUrlModels")
    public List<ChildUrlModel> getChildUrlModels() {
        return childUrlModels;
    }

    public void setChildUrlModels(List<ChildUrlModel> childUrlModels) {
        this.childUrlModels = childUrlModels;
    }
    
    
    @Column(unique = true,length = 32)
	public String getMd3() {
		return md3;
	}

	public void setMd3(String md3) {
		this.md3 = md3;
	}

	@Override
	public String toString() {
		
		return "url="+getUrl()+"\n"
				+ "是不是父url=="+isHaveChild+"\n"
						+ "孩子有多少=="+getChildUrlModels().size()+"\n"
						+ "孩子是不是没了=="+isChildFinish() + "\n"
								+ "MD3==" + getMd3();
	}
}
