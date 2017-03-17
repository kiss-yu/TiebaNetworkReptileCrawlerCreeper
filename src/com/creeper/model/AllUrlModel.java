package com.creeper.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by 11723 on 2016/12/26.
 */
public class AllUrlModel implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private String url;
    private boolean childFinish;
    private boolean isHaveChild;
    private List<ChildUrlModel> childUrlModels;
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

    public boolean isChildFinish() {
        return childFinish;
    }

    public void setChildFinish(boolean childFinish) {
        this.childFinish = childFinish;
    }

    public boolean isIsHaveChild() {
        return isHaveChild;
    }

    public void setIsHaveChild(boolean isHaveChild) {
        this.isHaveChild = isHaveChild;
    }

    public List<ChildUrlModel> getChildUrlModels() {
        return childUrlModels;
    }

    public void setChildUrlModels(List<ChildUrlModel> childUrlModels) {
        this.childUrlModels = childUrlModels;
    }
	
	@Override
	public String toString() {
		
		return "url="+getUrl()+"\n"
				+ "是不是父url=="+isHaveChild+"\n"
						+ "孩子有多少=="+getChildUrlModels().size()+"\n"
						+ "孩子是不是没了=="+isChildFinish();
	}
}
