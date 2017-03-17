package com.creeper.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.creeper.Exception.AddException;

public class HibernateUtil {

	private static SessionFactory SessionFactory;
	private static Session session;
	
	static{
		Configuration config=new Configuration().configure();
    	ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
    	SessionFactory=config.buildSessionFactory(serviceRegistry);
    	initSession();
	}
	
	public static void destory() {
		session.close();
		SessionFactory.close();
	}
	
	
	
	public static void initSession() {
		session=SessionFactory.openSession();
	}
	
	public  static Session getSession(){
		return session;
	}
	
	public static Session getNewSession() {
		Session session=SessionFactory.openSession();
		return session;
	}
	
	public static void sessionCommit(Transaction tx) throws Exception {
		try {
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
//			session.close();
			initSession();
			throw new Exception();
		}
		
		
	}
}
