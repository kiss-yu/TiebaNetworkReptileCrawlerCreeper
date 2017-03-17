package com.creeper.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
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
				session.save(childUrlModel);
				}
	}
	
	public void updata(ChildUrlModel model) {
		try {
			Session session=HibernateUtil.getSession();
			session.update(model);
			session.beginTransaction().commit();
			session.close();
		} catch (Exception e) {
			System.out.println(e.toString()+"==child更新失败");
		}
	}
	
	public void deleteAll() throws DeleteException {
		try {
			Session session=HibernateUtil.getSession();
			Query query = session.createQuery("delete from ChildUrlModel where 1=1");
			query.executeUpdate();
			session.beginTransaction().commit();
			session.close();
		} catch (Exception e) {
			throw new DeleteException(e.toString()+"==ChildUrl初始化失败");
		}
	}
	
	public ChildUrlModel getUrlChild(AllUrlModel pareant) {
		Session session=HibernateUtil.getSession();
		Criteria criteria=session.createCriteria(ChildUrlModel.class);
		try {
			ChildUrlModel model=(ChildUrlModel) criteria.add(Restrictions.and(Restrictions.eq("isGet", false),Restrictions.eq("pareantId", pareant.getId()))).list().get(0);
			session.close();
			return model;
		} catch (Exception e) {
			
		}
		return null;
	}

	
}
