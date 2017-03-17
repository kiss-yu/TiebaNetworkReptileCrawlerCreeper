package com.creeper.dao;


import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
		Session session=HibernateUtil.getNewSession();
		Transaction tx=session.beginTransaction();
		try {
//			System.out.println(model.toString());
			session.save(model);
			mapper.insert(model.getChildUrlModels(), session);
			tx.commit();
			session.close();
		} catch (Exception e) {
			tx.rollback();
			session.close();
			throw new AddException(e.toString()+"==AllUrl添加失败");
		}
	}
	
	public void update(AllUrlModel model) throws UpdateException {
		Session session=HibernateUtil.getNewSession();
		Transaction tx=session.beginTransaction();
		try {
			session.update(model);
			tx.commit();
			session.close();
		} catch (Exception e) {
			tx.rollback();
			session.close();
			throw new UpdateException(e.toString()+"==AllUrl更新失败");
		}
	}
	
	public void delete(AllUrlModel model) {
		Session session=HibernateUtil.getNewSession();
		Transaction tx=session.beginTransaction();
		try {
			session.delete(model);
			tx.commit();
			session.close();
		} catch (Exception e) {
			tx.rollback();
			session.close();
			System.out.println(e.toString()+"==AllUrl删除失败！！！");
		}
	}
	
	public void deleteAll() throws DeleteException {
		Session session=HibernateUtil.getNewSession();
		Transaction tx=session.beginTransaction();
		try {
			Query query = session.createQuery("delete from AllUrlModel where 1=1");
			query.executeUpdate();
			tx.commit();
			session.close();
		} catch (Exception e) {
			tx.rollback();
			session.close();
			throw new DeleteException(e.toString()+"==AllUrls初始化失败");
		}
	}
	
	
	
	public AllUrlModel getTargetUrlModel() {
		Session session=HibernateUtil.getNewSession();
		Transaction tx=session.beginTransaction();
		try {
	        Criteria criteria=session.createCriteria(AllUrlModel.class);
	        AllUrlModel model=((AllUrlModel)criteria.add(Restrictions.and(Restrictions.eq("isHaveChild", true),Restrictions.eq("childFinish", false))).list().get(0));
	        tx.commit();
	        session.close();
	        return model;
		} catch (Exception e) {
			tx.rollback();
			session.close();
		}
	return null;
	}

	
	public AllUrlModel selectByMd3(String md3) {
		Session session = HibernateUtil.getNewSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria criteria = session.createCriteria(AllUrlModel.class);
			AllUrlModel model = (AllUrlModel)criteria.add(Restrictions.eq("md3", md3)).uniqueResult();
			tx.commit();
			session.close();
			return model;
		} catch (Exception e) {
			tx.rollback();
			session.close();
		}
		return null;
	}
	
	public AllUrlModel selectByUrl(String url) {
		Session session=HibernateUtil.getNewSession();
		Transaction tx=session.beginTransaction();
		 try {
			 Criteria criteria=session.createCriteria(AllUrlModel.class);
			 AllUrlModel model=(AllUrlModel) criteria.add(Restrictions.eq("url", url)).uniqueResult();
			 tx.commit();
			 session.close();
			 return model;
		} catch (Exception e) {
			tx.rollback();
			session.close();
		}
		return null;
	}
	
	public AllUrlModel selectByIs(Boolean id) {
		Session session=HibernateUtil.getNewSession();
		Transaction tx=session.beginTransaction();
		 try {
			 Criteria criteria=session.createCriteria(AllUrlModel.class);
			 AllUrlModel model=(AllUrlModel) criteria.add(Restrictions.eq("CHILDFINISH", id)).list().get(0);
			tx.commit();
			 session.close();
			 return model;
		} catch (Exception e) {
			tx.rollback();
			session.close();
		}
		 
		return null;
	}
	
}
