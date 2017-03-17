package com.creeper.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.creeper.Exception.AddException;
import com.creeper.Exception.UpdateException;
import com.creeper.hibernate.util.HibernateUtil;
import com.creeper.model.AgoUrlsModel;
import com.creeper.model.PostModel;
import com.creeper.model.TiebaModel;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class TiebaMapper implements DaoMapper{
	
	PostsMapper postsMapper=new PostsMapper();
	
	public void insert(Object obj) throws AddException {
		try {
//			((TiebaModel)obj).setCreate_time(0);
//			System.out.println(((TiebaModel)obj).toString());
			Session session=HibernateUtil.getSession();
			session.save((TiebaModel)obj);
			postsMapper.insert(session,((TiebaModel)obj).getPostLists());
			session.beginTransaction().commit();
			session.close();
		} catch (Exception e) {
			throw new AddException(e.toString()+"==贴吧数据添加失败");
		}
	}
	
	public  void delete() {
		 
	}

	public void updata(Object obj) throws UpdateException {
		
	}

	public List<Object> select(Object obj) {
		try {
			Session session=HibernateUtil.getSession();
			Criteria criteria =session .createCriteria(TiebaModel.class, "u");
			List<Object>  list =criteria.add(Restrictions.eq("tieba_name",( (String)obj))).list();
			session.beginTransaction().commit();
			session.close();
			return list;
		} catch (Exception e) {
			return null;
		}
	}
	
	
	
}
