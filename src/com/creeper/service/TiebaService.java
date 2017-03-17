package com.creeper.service;


import com.creeper.Exception.AddException;
import com.creeper.dao.TiebaInforMapper;
import com.creeper.dao.TiebaMapper;
import com.creeper.model.TiebaInforModel;
import com.creeper.model.TiebaModel;

public class TiebaService {

	private TiebaMapper daoTieba=new TiebaMapper();
	private TiebaInforMapper inforMapper = new TiebaInforMapper();
	
	public void sava(TiebaModel model) {
		if (IlleageTieba(model)) {
			try {
				if (isAdd(model)) {
//					TiebaModel model2=daoTieba.selectByName(model.getTieba_name());
//					List<TiebaInforModel> inforModels=model2.getInforModels();
//					inforModels.addAll(model.getInforModels());
//					model2.setInforModels(inforModels);
//					daoTieba.updata(model2);
					Integer tiebaid = daoTieba.selectByMd3(model.getMd3()).getId();
					for(TiebaInforModel model2:model.getInforModels()){
						model2.setTiebaid(tiebaid);
						inforMapper.insert(model2);
					}
				} else {
					daoTieba.insert(model);
				}
			} catch (AddException e) {
				System.out.println(e.toString());
			}
		}
	}
	public boolean isAdd(TiebaModel tiebaModel) {
		if (daoTieba.selectByMd3(tiebaModel.getMd3()) == null)
			return false;
		return true;
	}
	
	private boolean IlleageTieba(TiebaModel model){
		try {
			if (model.getTieba_name().matches(".*吧.*")) {
				if (model.getInforModels().get(0).getPost_number() > 0){
					if (model.getInforModels().get(0).getTieba_user_number() > 0){
						return true;
					}
				}
			}
		} catch (Exception e) {
			
		}
		return false;
	}
	
	
	
}
