package com.creeper.dao;

import com.creeper.Exception.AddException;
import com.creeper.hibernate.util.HibernateUtil;
import com.creeper.model.TiebaInforModel;
import com.creeper.model.TiebaModel;

import sun.security.timestamp.TSRequest;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

/**
 * Created by 11723 on 2016/12/29.
 */
public class TiebaInforMapper {
    private PostsMapper mapper=new PostsMapper();
    public void inser(List<TiebaInforModel> models, Session session) throws AddException {
       try {
           for (TiebaInforModel inforModel:models){
               session.save(inforModel);
               mapper.insert(session,inforModel.getPostModels());
           }
       }catch (Exception e){
           throw new AddException(e.toString()+"==tiebaInfor添加失败");
       }
    }
    
    public void insert(TiebaInforModel model) {
		Session session = HibernateUtil.getNewSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(model);
			tx.commit();
			session.close();
		} catch (Exception e) {
			tx.rollback();
			session.close();
		}
	}

}
