package com.creeper.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.creeper.Exception.DeleteException;
import com.creeper.hibernate.util.HibernateUtil;
import com.creeper.model.AllUrlModel;
import com.creeper.model.ChildUrlModel;

public class ChildUrlMapper implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public void insert(List<ChildUrlModel> models,Session session) {
			for (ChildUrlModel childUrlModel : models) {
				session.saveOrUpdate(childUrlModel);
				}
	}
	
	public void updata(ChildUrlModel model) {
		Session session=HibernateUtil.getNewSession();
		Transaction tx=session.beginTransaction();
		try {
			session.update(model);
			tx.commit();
			session.close();
		} catch (Exception e) {
			tx.rollback();
			session.close();
			System.out.println(e.toString()+"==child更新失败");
		}
	}
	
	public void deleteAll() throws DeleteException {
		Session session=HibernateUtil.getNewSession();
		Transaction tx=session.beginTransaction();
		try {
			Query query = session.createQuery("delete from ChildUrlModel where 1=1");
			query.executeUpdate();
			tx.commit();
			session.close();
		} catch (Exception e) {
			tx.rollback();
			session.close();
			throw new DeleteException(e.toString()+"==ChildUrl初始化失败");
		}
	}
	
	public ChildUrlModel getUrlChild(AllUrlModel pareant) {
		Session session=HibernateUtil.getNewSession();
		Transaction tx=session.beginTransaction();
		try {
			Criteria criteria=session.createCriteria(ChildUrlModel.class);
			ChildUrlModel model=(ChildUrlModel) criteria.add(Restrictions.and(Restrictions.eq("isGet", false),Restrictions.eq("allUrlModel.id", pareant.getId()))).list().get(0);
			tx.commit();
			session.close();
			return model;
		} catch (Exception e) {
			tx.rollback();
			session.close();
		}
		
		return null;
	}
//	public List selectIdByUrl(ChildUrlModel model) {
//		Session session=HibernateUtil.getNewSession();
//		Transaction tx=session.beginTransaction();
//		List list=new ArrayList();
//		try {
//			Criteria criteria=session.createCriteria(ChildUrlModel.class);
//			 list= criteria.add(Restrictions.eq("url", model.getUrl())).list();
//			tx.commit();
//			session.close();
//		} catch (Exception e) {
//			tx.rollback();
//			session.close();
//		}
//		return list;
//	}
//	
}
