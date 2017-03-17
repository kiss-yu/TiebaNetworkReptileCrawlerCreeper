package com.creeper.test;


import com.creeper.Exception.AddException;
import com.creeper.dao.TiebaMapper;
import com.creeper.getshuju.Start;
import com.creeper.hibernate.util.HibernateUtil;
import com.creeper.model.AllUrlModel;
import com.creeper.model.ChildUrlModel;
import com.creeper.model.TiebaInforModel;
import com.creeper.model.TiebaModel;
import com.creeper.util.MyTool;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main1 {

	public static void main(String[] args) throws AddException, IOException{
		new Test2("https://tieba.baidu.com/").main();
		
	}

}
