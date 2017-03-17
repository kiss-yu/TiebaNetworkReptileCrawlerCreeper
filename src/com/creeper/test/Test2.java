package com.creeper.test;
import com.creeper.getshuju.Start;
import com.creeper.getshuju.Tieba;
import com.creeper.model.TiebaModel;
import com.creeper.service.TiebaService;
import com.creeper.service.TiebaUrlService;



public class Test2 {
	private Start start=new Start();
	private TiebaService tiebaService=new TiebaService();
	private TiebaUrlService tiebaUrlService=new TiebaUrlService();
	private String rootUrl;
	
	public Test2(String rootUrl){
		this.rootUrl=rootUrl;
	}
	
	public void main()  {
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				new ThreadMain(rootUrl).start();
//			}
//		}).start();
			while(true){
				try {
					String url=tiebaUrlService.getCallUrl();
					System.out.println("贴吧URL="+url);
					start.start(url);
     				getTiebaModel(url);
			}catch (Exception e) {
				System.out.println(e.toString()+"\n===开始休眠===");
				try {
					Thread.sleep(10*1000);
				} catch (InterruptedException e1) {
					System.out.println("睡眠失败！！！");
				}
			}
		}
	}
	
	private void getTiebaModel(String url){
			TiebaModel tiebaModel=new Tieba(url).getTiebaModel();
			tiebaService.sava(tiebaModel);
	}
	
}
