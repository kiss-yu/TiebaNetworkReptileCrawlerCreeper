package com.creeper.service;

import com.creeper.Exception.AddException;
import com.creeper.Exception.UpdateException;
import com.creeper.dao.TiebaUrlMapper;
import com.creeper.model.TiebaUrlModel;

public class TiebaUrlService {

	private TiebaUrlMapper mapper=new TiebaUrlMapper();
	
	public void save(TiebaUrlModel model) {
		try {
			if (!isHave(model.getUrl())) {
				mapper.insert(model);
			}else {
			}
		} catch (AddException e) {
			System.out.println(e.toString());
		}
	}
	
	public boolean isHave(String url) {
		if (mapper.selectByUrl(url) == null) {
			return false;
		}
		return true;
	}

	public String getCallUrl() throws UpdateException {
		TiebaUrlModel model=mapper.getCallUrl();
		if (model == null) {
			mapper.updateAll();
			System.out.println("==更新了==");
			return null;
		}else {
			model.setGet(true);
			mapper.update(model);
			return model.getUrl();
		}
	}
	
}
