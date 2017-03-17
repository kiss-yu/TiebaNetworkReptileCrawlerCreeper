package com.creeper.service;

import com.creeper.Exception.AddException;
import com.creeper.Exception.DeleteException;
import com.creeper.dao.AgoUrlsMapper;
import com.creeper.model.AgoUrlsModel;

public class AgoUrlsService {

	private AgoUrlsMapper daoAgo=new AgoUrlsMapper();
	
	public void init() throws DeleteException  {
		daoAgo.delete();
	}
	
	public boolean isHava(AgoUrlsModel agoUrlsModel) {
		agoUrlsModel.setId(0);
		if (daoAgo.select(agoUrlsModel) == null || daoAgo.select(agoUrlsModel).size() == 0) {
			return false;
		}
		return true;
	}
	
	public void save(AgoUrlsModel agoUrlsModel) throws AddException {
		daoAgo.insert(agoUrlsModel);
	}
	
}
