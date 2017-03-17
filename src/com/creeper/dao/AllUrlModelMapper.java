package com.creeper.dao;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.creeper.Exception.AddException;
import com.creeper.Exception.DeleteException;
import com.creeper.Exception.UpdateException;
import com.creeper.hibernate.util.HibernateUtil;
import com.creeper.model.AllUrlModel;

/**
 * Created by 11723 on 2016/12/26.
 */
public class AllUrlModelMapper {
	
	private ChildUrlMapper mapper=new ChildUrlMapper();
	public void insert(AllUrlModel model) throws AddException {
		Session session=HibernateUtil.getSession();
		try {
			session.save(model);
			mapper.insert(model.getChildUrlModels(), session);
			session.beginTransaction().commit();
			session.close();
		} catch (Exception e) {
			session.close();
			throw new AddException(e.toString()+"AllUrl添加失败");
		}
	}
	
	public void update(AllUrlModel model) throws UpdateException {
		try {
			Session session=HibernateUtil.getSession();
			session.update(model);
			session.beginTransaction().commit();
			session.close();
		} catch (Exception e) {
			throw new UpdateException(e.toString()+"==AllUrl更新失败");
		}
	}
	
	public void delete(AllUrlModel model) {
		try {
			Session session=HibernateUtil.getSession();
			session.delete(model);
			session.beginTransaction().commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.toString()+"==AllUrl删除失败！！！");
		}
	}
	
	public void deleteAll() throws DeleteException {
		try {
			Session session=HibernateUtil.getSession();
			Query query = session.createQuery("delete from AllUrlModel where 1=1");
			query.executeUpdate();
			session.beginTransaction().commit();
			session.close();
		} catch (Exception e) {
			throw new DeleteException(e.toString()+"==AllUrls初始化失败");
		}
	}
	
	
	
	public AllUrlModel getTargetUrlModel() {
		try {
			Session session=HibernateUtil.getSession();
	        Criteria criteria=session.createCriteria(AllUrlModel.class);
	        AllUrlModel model=((AllUrlModel)criteria.add(Restrictions.and(Restrictions.eq("isHaveChild", true),Restrictions.eq("childFinish", false))).list().get(0));
	        session.close();
	        return model;
		} catch (Exception e) {
		}
	return null;
	}

	
	
	
	public AllUrlModel selectByUrl(String url) {
		Session session=HibernateUtil.getSession();
		 Criteria criteria=session.createCriteria(AllUrlModel.class);
		 try {
			 AllUrlModel model=(AllUrlModel) criteria.add(Restrictions.eq("url", url)).list().get(0);
			 session.close();
			 return model;
		} catch (Exception e) {
		
		}
		return null;
	}
	
	public AllUrlModel selectByIs(Boolean id) {
		Session session=HibernateUtil.getSession();
		 Criteria criteria=session.createCriteria(AllUrlModel.class);
		 try {
			 AllUrlModel model=(AllUrlModel) criteria.add(Restrictions.eq("CHILDFINISH", id)).list().get(0);
			 session.close();
			 return model;
		} catch (Exception e) {
		
		}
		return null;
	}
	
}
