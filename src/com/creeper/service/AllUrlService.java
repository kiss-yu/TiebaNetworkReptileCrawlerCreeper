package com.creeper.service;

import com.creeper.Exception.AddException;
import com.creeper.Exception.DeleteException;
import com.creeper.Exception.UpdateException;
import com.creeper.dao.AllUrlModelMapper;
import com.creeper.dao.ChildUrlMapper;
import com.creeper.model.AllUrlModel;
import com.creeper.model.ChildUrlModel;

public class AllUrlService {

	private AllUrlModelMapper mapper=new AllUrlModelMapper();
	private ChildUrlMapper childMapper=new ChildUrlMapper();
	
	public void sava(AllUrlModel model) {
		try {
			if (!isHave(model.getMd3())) {
				model.setIsHaveChild(true);
				model.setChildFinish(false);
				mapper.insert(model);
			}
		} catch (AddException e) {
			System.out.println("AllUrl添加失败！！！！");
		}
	}
	
	public String getUrl() throws UpdateException {
		AllUrlModel pareant=mapper.getTargetUrlModel();
		if (pareant == null) {
			return null;
		}if (childMapper.getUrlChild(pareant) == null) {
			pareant.setChildFinish(true);
			mapper.update(pareant);
			return getUrl();
		}
		ChildUrlModel model=updataChildUrl(childMapper.getUrlChild(pareant));
		if (isGet(model)) {
			getUrl();
		}
		return model.getUrl();
	}
	
	private ChildUrlModel updataChildUrl(ChildUrlModel model) {
		model.setIsGet(true);
		childMapper.updata(model);
		return model;
	}
	
	private boolean isHave(String url) {
		if (mapper.selectByMd3(url) != null) {
			return true;
		}
		return false;
	}
	
	
	public void init() {
		try {
			childMapper.deleteAll();
			mapper.deleteAll();
		} catch (DeleteException e) {
			e.printStackTrace();
		}
	}
	
	private boolean isGet(ChildUrlModel model) {
		if(mapper.selectByUrl(model.getUrl()) != null){
			 return true;
		}
		return false;
	}
}
