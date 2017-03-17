package com.creeper.getshuju;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import com.creeper.model.ChildUrlModel;
import com.creeper.model.TiebaUrlModel;

public class Url {


	private static Document ROOTDOC;

	protected static Document getROOTDOC() {
		return ROOTDOC;
	}

	protected static void setROOTDOC(Document rOOTDOC) {
		ROOTDOC = rOOTDOC;
	}

	public Map<String, Object> getUrlMap() {

		List<String> list=new ArrayList<>();
		for(int i = 0; i < ROOTDOC.select("a").size();i++){
			list.add(ROOTDOC.select("a").get(i).attr("abs:href"));
		}
		return getMap(list);
	}



	private Map<String, Object> getMap(List<String> strings){
		Map<String, Object> map=new HashMap<>();
		List<TiebaUrlModel> tiebaUrlModels=new ArrayList<>();
		List<ChildUrlModel> childUrlModels=new ArrayList<>();
		for (int i = 0; i < strings.size(); i++) {
			if (isUrl(strings.get(i))) {
				if (isIleageTiebaUrl(strings.get(i))) {
					tiebaUrlModels.add(geTiebaUrlModel(strings.get(i)));
				}else if (isIleageAllUrl(strings.get(i))) {
					childUrlModels.add(getChildUrlModel(strings.get(i)));
				}else {
					strings.remove(i);
					i--;
				}
			}else {
				strings.remove(i);
				i--;
			}

		}
		map.put("allurls", childUrlModels);
		map.put("tiebaurl", tiebaUrlModels);
		return map;
	}


	public String getTitle() {
		return ROOTDOC.title();
	}




	private TiebaUrlModel geTiebaUrlModel(String url) {
		TiebaUrlModel model=new TiebaUrlModel();
		model.setIsGet(false);
		model.setUrl(url);
		return model;
	}

	private ChildUrlModel getChildUrlModel(String url) {
		ChildUrlModel model=new ChildUrlModel();
		model.setUrl(url);
		model.setIsGet(false);
		return model;
	}

	private boolean isIleageAllUrl(String string) {
		if (string.matches("http[s]?://tieba.baidu.com/.*[^/#]")) {
			return true;
		}

		return false;
	}

	private boolean isIleageTiebaUrl(String string) {
		if (string.matches("http[s]?://tieba.baidu.com/[^/=]+[=]{1}+[^/=]+[^/#]")) {
			return true;
		}
		return false;
	}
	private boolean isUrl(String string) {
		if (!string.matches("http[s]?://tieba.baidu.com/.*")) {
			return false;
		}if (string.matches("http[s]?://tieba.baidu.com/p/[\\d]{3}.*")) {
			return false;
		}if (string.matches("http[s]?://tieba.baidu.com/.*un=.*")) {
			return false;
		}if (string.matches("http[s]?://tieba.baidu.com/.*un=.*")) {
			return false;
		}if (string.matches("http[s]?://tieba.baidu.com/.*search")) {
			return false;
		}
		return true;
	}
}
