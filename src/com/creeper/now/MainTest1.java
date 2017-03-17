package com.creeper.now;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.creeper.Exception.DeleteException;
import com.creeper.getshuju.Start;
import com.creeper.model.TiebaInforModel;
import com.creeper.model.TiebaModel;
import com.creeper.service.TiebaService;
import com.creeper.service.TiebaUrlService;

public class MainTest1 {

	public static void main(String[] args) {
			TiebaInforModel one = new TiebaInforModel();
			TiebaInforModel two = new TiebaInforModel();
			TiebaModel model = new TiebaModel();
			one.setTieba_user_number(100);
			two.setTieba_user_number(200);
			one.setPost_number(100);
			two.setPost_number(200);
			model.setTieba_name("百度贴吧");
			TiebaService service = new TiebaService();
			List<TiebaInforModel> list = new ArrayList<>();
			list.add(one);
			model.setInforModels(list);
			service.sava(model);
			TiebaModel model1 = new TiebaModel();
			model1.setTieba_name("百度贴吧");
			list.clear();
			list.add(two);
			model1.setInforModels(list);
			service.sava(model1);
		
	}

}
