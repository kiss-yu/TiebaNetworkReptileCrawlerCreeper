package com.creeper.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil {

	private static SessionFactory SessionFactory;
	
	static{
		Configuration config=new Configuration().configure();
    	ServiceRegistry serviceRegistry=new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
    	SessionFactory=config.buildSessionFactory(serviceRegistry);
    	
	}
	
	public static void destory() {
		SessionFactory.close();
	}
	
	public  static Transaction close(Session session) {
		return session.beginTransaction();
	}
	
	public  static Session getSession(){
		Session session=SessionFactory.openSession();
		return session;
	}
}
