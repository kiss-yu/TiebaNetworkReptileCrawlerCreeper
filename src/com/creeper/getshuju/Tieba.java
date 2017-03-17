package com.creeper.getshuju;



import com.creeper.model.TiebaModel;


public class Tieba extends TopPareant{

	private TiebaModel tiebaModel=new TiebaModel();

	public Tieba(String url){
		tiebaModel.setUrl(url);
		setTitle();
		setInforSets();
	}

	private void setInforSets(){
		tiebaModel.setInforModels(new TiebaInfor(tiebaModel.getTieba_name()).getInforSets());
	}

	
	private void setTitle() {
		String title=ROOTDOC.title();
		try {
			tiebaModel.setTieba_name(getTitleOne(title));
		} catch (Exception e) {
			try {
				tiebaModel.setTieba_name(getTitleTwo(title));
			} catch (Exception e1) {
				tiebaModel.setTieba_name(getTitleThree(title));
			}
		}
		
	}
	private String getTitleTwo(String title) throws Exception{
		if (title.length() < 3 || !title.matches(".*吧")) {
			throw new Exception();
		}
		return title;
	}
	
	private String getTitleThree(String title){
		try {
			return ROOTDOC.getElementsByClass("header").first()
					.getElementById("pagelet_frs-header/pagelet/head")
					.select("a.pagelet_frs-header/pagelet/head").text();
		} catch (Exception e) {
			return null;
		}
	}
	
	
	
	
	private String getTitleOne(String title) throws Exception{
		try {
			String[] str=title.split("_");
			return str[str.length-2];
		} catch (Exception e) {
			throw new Exception();
		}
	}
	
	public TiebaModel getTiebaModel() {
		return tiebaModel;
	}
	
}
