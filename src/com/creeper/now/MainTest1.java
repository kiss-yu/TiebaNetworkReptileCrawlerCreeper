package com.creeper.now;

import java.io.IOException;

import com.creeper.Exception.DeleteException;
import com.creeper.getshuju.Start;

public class MainTest1 {

	public static void main(String[] args) throws DeleteException {
		String url="http://tieba.baidu.com/f/index/forumpark?cn=%E4%B8%9C%E5%8D%97%E4%BA%9A%E7%94%B5%E8%A7%86%E5%89%A7&ci=0&pcn=%E7%94%B5%E8%A7%86%E5%89%A7&pci=0&ct=1&rn=20&pn=1";
		Start start=new Start();
		try {
			start.start(url);
//			for (String string : new UrlList().getUrlList()) {
//				System.out.println(string);
//			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
