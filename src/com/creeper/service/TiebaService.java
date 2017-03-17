package com.creeper.service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.creeper.Exception.AddException;
import com.creeper.dao.TiebaMapper;
import com.creeper.model.TiebaModel;

public class TiebaService {

	private TiebaMapper daoTieba=new TiebaMapper();
	
	public void sava(TiebaModel model) {
		if (isAdd(model)) {
			try {
				daoTieba.insert(model);
				System.out.println("\n====确认添加的贴吧===\n==="+model.getTieba_name()+"===\n");
			} catch (AddException e) {
			}
		}
	}
	public boolean isAdd(TiebaModel tiebaModel) {
		List<Object> list=load(tiebaModel.getTieba_name());
		try {
		} catch (Exception e) {
			
		}
		if (!IlleageTieba(tiebaModel)) {
			return false;
		}
		if (list == null || list.size() == 0) {
			return true;
		}if (time(tiebaModel, (TiebaModel)list.get(list.size()-1))) {
			return true;
		}
		return false;
	}
	
	public List<Object> load(String tiebaname) {
		List<Object> list=daoTieba.select(tiebaname);
		return list;
	}
	
	public boolean time(TiebaModel model1,TiebaModel model2) {
		Calendar calendar1=new GregorianCalendar();
		Calendar calendar2=new GregorianCalendar();
		calendar1.setTime(model1.getCreate_time());
		calendar2.setTime(model2.getCreate_time());
		if ((calendar1.getTimeInMillis() - calendar2.getTimeInMillis()) > 60*60*1000 || 
				(calendar1.getTimeInMillis() - calendar2.getTimeInMillis()) >  60*60*1000) {
			return true;
		}
		return false;
	}
	
	private boolean IlleageTieba(TiebaModel model){
		try {
			if (model.getTieba_name().matches(".*吧.*")) {
				if (model.getTieba_user_number() > 10) {
					if (model.getPost_number() > 10) {
						if (model.getPostLists().size() >1) {
							return true;
						}
					}
				}
			}
		} catch (Exception e) {
		}
		
		return false;
	}
	
	
	
}
