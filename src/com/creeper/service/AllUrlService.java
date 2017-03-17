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
			if (!isHave(model.getUrl())) {
				model.setIsHaveChild(true);
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
		return updataChildUrl(childMapper.getUrlChild(pareant));
	}
	
	private String updataChildUrl(ChildUrlModel model) {
		model.setGet(true);
		childMapper.updata(model);
		return model.getUrl();
	}
	
	private boolean isHave(String url) {
		if (mapper.selectByUrl(url) != null) {
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
}
