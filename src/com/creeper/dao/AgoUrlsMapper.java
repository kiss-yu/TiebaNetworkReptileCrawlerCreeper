package com.creeper.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.creeper.Exception.AddException;
import com.creeper.Exception.DeleteException;
import com.creeper.Exception.UpdateException;
import com.creeper.hibernate.util.HibernateUtil;
import com.creeper.model.AgoUrlsModel;


public class AgoUrlsMapper implements DaoMapper{

	public void insert(Object agoUrlsModel) throws AddException{
		try {
			Session session=HibernateUtil.getSession();
			session.save((AgoUrlsModel)agoUrlsModel);
			session.beginTransaction().commit();
			session.close();
		} catch (Exception e) {
			throw new AddException(e.toString()+"===AgoUrl添加失败");
		}
	}
	
	public void delete() throws DeleteException{
		try {
			Session session=HibernateUtil.getSession();
			Query query = session.createQuery("delete from AgoUrlsModel where 1=1");
			query.executeUpdate();
			session.beginTransaction().commit();
			session.close();
		} catch (Exception e) {
			throw new DeleteException(e.toString()+"==AgoUrls初始化失败");
		}
	}

	@Override
	public void updata(Object obj) throws UpdateException {
		
	}

	@Override
	public List<Object> select(Object obj) {
		AgoUrlsModel ago=(AgoUrlsModel)obj;
		try {
			Session session=HibernateUtil.getSession();
			if (ago.getId() == 0) {
				Criteria criteria = session.createCriteria(AgoUrlsModel.class, "u");
				List<Object>  list =criteria.add(Restrictions.eq("urls", ago.getUrls())).list();
				session.beginTransaction().commit();
				session.close();
				return list;
			}else {
				Criteria criteria =session .createCriteria(AgoUrlsModel.class, "u");
				List<Object>  list =criteria.add(Restrictions.gt("id", ago.getId())).list();
				session.beginTransaction().commit();
				session.close();
				return list;
			}
		} catch (Exception e) {
			return null;
		}
	}
	


	
}
