package com.creeper.dao;

import com.creeper.Exception.AddException;
import com.creeper.Exception.DeleteException;
import com.creeper.Exception.UpdateException;
import com.creeper.hibernate.util.HibernateUtil;
import com.creeper.model.TiebaUrlModel;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Created by 11723 on 2016/12/26.
 */
public class TiebaUrlMapper {



    public void insert(TiebaUrlModel model) throws AddException{
	 	Session session=HibernateUtil.getNewSession();
	 	Transaction tx=session.beginTransaction();
    	try {
    	        session.save(model);
    	        tx.commit();
    	        session.close();
		} catch (Exception e) {
			tx.rollback();
			 session.close();
			throw new AddException(e.toString()+"==TiebaUrl添加失败");
		}
    }

    public TiebaUrlModel getCallUrl(){
	 	Session session=HibernateUtil.getNewSession();
	 	Transaction tx=session.beginTransaction();
    	try {
    	        Criteria criteria=session.createCriteria(TiebaUrlModel.class);
    	        TiebaUrlModel model=(TiebaUrlModel) criteria.add(Restrictions.eq("isGet",false)).list().get(0);
    	        tx.commit();
   			 	session.close();
    	        return model;
		} catch (Exception e) {
			tx.rollback();
			 session.close();
		}
       return null;
    }
    
    public TiebaUrlModel selectByUrl(String url) {
		Session session=HibernateUtil.getNewSession();
		Transaction tx=session.beginTransaction();
    	try {
    		Criteria criteria=session.createCriteria(TiebaUrlModel.class);
    		TiebaUrlModel model= (TiebaUrlModel) criteria.add(Restrictions.eq("url",url)).list().get(0);
    		tx.commit();
			 session.close();
    		return model;
		} catch (Exception e) {
			tx.rollback();
			 session.close();
		}
    	return null;
	}
    
    
    public void update(TiebaUrlModel model) throws UpdateException {
		Session session=HibernateUtil.getNewSession();
		Transaction tx=session.beginTransaction();
    	try {
    		session.update(model);
    		tx.commit();
			 session.close();
		} catch (Exception e) {
			tx.rollback();
			 session.close();
			throw new UpdateException(e.toString()+"TiebaUrl更新失败");
		}
	}
    
    public void updateAll() throws UpdateException {
		Session session=HibernateUtil.getNewSession();
		Transaction tx=session.beginTransaction();
		try {
			Query query = session.createQuery("update  TiebaUrlModel set isGet = false where 1=1");
			query.executeUpdate();
			tx.commit();
			 session.close();
		} catch (Exception e) {
			tx.rollback();
			 session.close();
			throw new UpdateException(e.toString()+"==所有TiebaUrl更新失败");
		}
	}
    
    public TiebaUrlModel selectByMd3(String md3) {
		Session session = HibernateUtil.getNewSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria criteria = session.createCriteria(TiebaUrlModel.class);
			TiebaUrlModel model = (TiebaUrlModel)criteria.add(Restrictions.eq("md3", md3)).uniqueResult();
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
