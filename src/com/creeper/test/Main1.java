package com.creeper.test;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.creeper.Exception.AddException;
import com.creeper.Exception.DeleteException;
import com.creeper.dao.AgoUrlsMapper;
import com.creeper.dao.AllUrlModelMapper;
import com.creeper.dao.TiebaMapper;
import com.creeper.dao.TiebaUrlMapper;
import com.creeper.hibernate.util.HibernateUtil;
import com.creeper.model.AgoUrlsModel;
import com.creeper.model.AllUrlModel;
import com.creeper.model.ChildUrlModel;
import com.creeper.model.PostModel;
import com.creeper.model.Test;
import com.creeper.model.TiebaModel;
import com.creeper.service.AgoUrlsService;


public class Main1 {

	public static void main(String[] args){
		
//		String url="http://tieba.baidu.com/f?ie=utf-8&kw=麻将#app_forum_top";
//		
//		if (url.matches("http[s]?://tieba.baidu.com/[^/=]+[=]{1}[^/=]+[=]?[^/=]+[^/#]")){
//			System.out.println("成功");
//		}else {
//			System.out.println("失败");
//		}
		
//		boolean a=false;
//		String aString="false";
//		System.out.println(aString.equals("false"));
		
//		String string="人数是12,152,165人";
//		
//		try {
//			Pattern p = Pattern.compile("[[\\d]+[,|-]{1}]+[\\d]+");
//			Matcher m = p.matcher(string);
//			while(m.find()){
//			   System.out.println( Integer.valueOf(m.group().replaceAll("[\\D]", "")));  
//		  }
//		} catch (Exception e) {
//		}
		
//		Session session=HibernateUtil.getSession();
//		 Criteria criteria=session.createCriteria(AllUrlModel.class);
//		List a=criteria.add(Restrictions.eq("isHaveChild", true)).list();
//		System.out.println(a.size());
//		session.close();
//		Session session1=HibernateUtil.getSession();
//		 Criteria criteria1=session1.createCriteria(ChildUrlModel.class);
//		a=criteria1.add(Restrictions.and(Restrictions.eq("isGet", false),Restrictions.eq("pareantId",( (AllUrlModel)a.get(0)).getId()))).list();
//		System.out.println(((ChildUrlModel)a.get(10)).getUrl());
//		session1.close();
		
//		TiebaUrlMapper mapper=new TiebaUrlMapper();
//		System.out.println(mapper.getCallUrl().getUrl());
		
		Set<PostModel> sets=new HashSet<>();
		PostModel model=new PostModel();
		PostModel model1=new PostModel();
		model.setId(0);
		model1.setId(0);
		model.setSort(125615);
		model1.setSort(125615);
		sets.add(model);
		sets.add(model1);
		System.out.println(sets.size());
		
	}
	
	private static boolean isHavaNumber(String string){
		return string.matches(".*[\\d]{3,}.*");
	}
}
