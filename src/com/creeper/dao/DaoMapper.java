package com.creeper.dao;

import java.util.List;

import com.creeper.Exception.AddException;
import com.creeper.Exception.DeleteException;
import com.creeper.Exception.UpdateException;

public interface DaoMapper {

	void insert(Object obj) throws AddException;
	
	void delete() throws DeleteException;
	
	void updata(Object obj) throws UpdateException;
	
	List< Object> select(Object obj);
	
}
