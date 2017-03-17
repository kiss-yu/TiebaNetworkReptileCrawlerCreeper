package com.creeper.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.creeper.Exception.AddException;
import com.creeper.Exception.UpdateException;
import com.creeper.hibernate.util.HibernateUtil;
import com.creeper.model.TiebaModel;
import com.creeper.model.TiebaUrlModel;

public class TiebaMapper{
	
	TiebaInforMapper mapper=new TiebaInforMapper();
	
	public void insert(Object obj) throws AddException {
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		try {
			session.save((TiebaModel)obj);
			mapper.inser(((TiebaModel)obj).getInforModels(),session);
			HibernateUtil.sessionCommit(tx);
			System.out.println("\n贴吧添加成功=="+((TiebaModel)obj).getTieba_name()+"\n");
		} catch (Exception e) {
			throw new AddException(e.toString()+"=="+((TiebaModel)obj).getTieba_name()+"==数据添加失败");
		}
	}
	
	public  void delete() {
		 
	}

	public void updata(Object obj) throws UpdateException {
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		try{
			session.update((TiebaModel)obj);
			mapper.inser(((TiebaModel)obj).getInforModels(),session);
			HibernateUtil.sessionCommit(tx);
			System.out.println("\n==更新贴吧成功=="+((TiebaModel)obj).getTieba_name()+"\n");
		} catch (Exception e) {
			throw new UpdateException(e.toString()+"==贴吧更新添加失败=="+((TiebaModel)obj).getTieba_name()+"\n");
		}
	}



	public TiebaModel selectByName(String tiebaName) {
		Session session=HibernateUtil.getSession();
		Transaction tx=session.beginTransaction();
		try{
			Criteria criteria =session .createCriteria(TiebaModel.class, "u");
			TiebaModel model=(TiebaModel)criteria.add(Restrictions.eq("tieba_name",tiebaName)).uniqueResult();
			HibernateUtil.sessionCommit(tx);
			return  model;
		} catch (Exception e) {
			return null;
		}
	}
	public TiebaModel selectByMd3(String md3) {
		Session session = HibernateUtil.getNewSession();
		Transaction tx = session.beginTransaction();
		try {
			Criteria criteria = session.createCriteria(TiebaModel.class);
			TiebaModel model = (TiebaModel)criteria.add(Restrictions.eq("md3", md3)).uniqueResult();
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
