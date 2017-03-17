package com.creeper.test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.creeper.Exception.UpdateException;
import com.creeper.getshuju.Start;
import com.creeper.getshuju.Url;
import com.creeper.model.AllUrlModel;
import com.creeper.model.ChildUrlModel;
import com.creeper.model.TiebaUrlModel;
import com.creeper.service.AllUrlService;
import com.creeper.service.TiebaUrlService;

public class ThreadMain{

	private String rootUrl=new String();
	private Start start=new Start();
	private Url url=new Url();
	private AllUrlModel rootModel=new AllUrlModel();
	private AllUrlService allUrlService=new AllUrlService();
	private TiebaUrlService tiebaUrlService=new TiebaUrlService();
	
	public ThreadMain(String rootUrl){
//		allUrlService.init();
		this.rootUrl=rootUrl;
	}
	
	public AllUrlModel init() {
		rootModel.setUrl(rootUrl);
		rootModel.setChildFinish(false);
		rootModel.setIsHaveChild(true);
		return rootModel;
	}
	
	public void start() {
		while(true){
			try {
				start.startThread(getUrl());
				System.out.println("url=="+rootModel.getUrl());
				savaMap(url.getUrlMap());
			} catch (Exception e) {
				System.out.println(e.toString()+"    url错误！！！");
			}
		}
	}
	
	public String getUrl() throws UpdateException {
		String url=allUrlService.getUrl();
		if (url == null ) {
			System.out.println("=====初始化=====");
			return init().getUrl();
		}
		return setRootModel(url);
	}
	
	public String setRootModel(String url) {
		rootModel.setUrl(url);
		rootModel.setChildFinish(false);
		rootModel.setIsHaveChild(true);
		return url;
	}
	
	
	@SuppressWarnings("unchecked")
	public void savaMap(Map<String, Object> map) {
		rootModel.setChildUrlModels((List<ChildUrlModel>)map.get("allurls"));
		allUrlService.sava(rootModel);
		List<TiebaUrlModel> list=(List<TiebaUrlModel>)map.get("tiebaurl");
		for (TiebaUrlModel model : list) {
			tiebaUrlService.save(model);
		}
	}
}
