package com.creeper.dao;

import com.creeper.Exception.AddException;
import com.creeper.Exception.DeleteException;
import com.creeper.Exception.UpdateException;
import com.creeper.hibernate.util.HibernateUtil;
import com.creeper.model.TiebaUrlModel;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Created by 11723 on 2016/12/26.
 */
public class TiebaUrlMapper {



    public void insert(TiebaUrlModel model) throws AddException{
    	try {
    		 	Session session=HibernateUtil.getSession();
    	        session.save(model);
    	        session.beginTransaction().commit();
    	        session.close();
		} catch (Exception e) {
			throw new AddException(e.toString()+"==TiebaUrl添加失败");
		}
       
    }

    public TiebaUrlModel getCallUrl(){
    	try {
    		 	Session session=HibernateUtil.getSession();
    	        Criteria criteria=session.createCriteria(TiebaUrlModel.class);
    	        TiebaUrlModel model=(TiebaUrlModel) criteria.add(Restrictions.eq("isGet",false)).list().get(0);
    	        session.close();
    	        return model;
		} catch (Exception e) {
		}
       return null;
    }
    
    public TiebaUrlModel selectByUrl(String url) {
    	try {
    		Session session=HibernateUtil.getSession();
    		Criteria criteria=session.createCriteria(TiebaUrlModel.class);
    		TiebaUrlModel model= (TiebaUrlModel) criteria.add(Restrictions.eq("url",url)).list().get(0);
    		session.close();
    		return model;
		} catch (Exception e) {
		}
    	return null;
	}
    
    
    public void update(TiebaUrlModel model) throws UpdateException {
    	try {
    		Session session=HibernateUtil.getSession();
    		session.update(model);
    		session.beginTransaction().commit();
    		session.close();
		} catch (Exception e) {
			throw new UpdateException(e.toString()+"TiebaUrl更新失败");
		}
	}
    
    public void updateAll() throws UpdateException {
		try {
			Session session=HibernateUtil.getSession();
			Query query = session.createQuery("update  TiebaUrlModel set ISGET = false where 1=1");
			query.executeUpdate();
			session.beginTransaction().commit();
			session.close();
		} catch (Exception e) {
			throw new UpdateException(e.toString()+"==所有TiebaUrl更新失败");
		}
	}
    
}
