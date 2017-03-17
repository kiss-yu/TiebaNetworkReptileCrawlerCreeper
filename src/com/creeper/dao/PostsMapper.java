package com.creeper.dao;

import java.util.List;

import org.hibernate.Session;

import com.creeper.Exception.AddException;
import com.creeper.Exception.DeleteException;
import com.creeper.Exception.UpdateException;
import com.creeper.model.PostModel;

public class PostsMapper{

	public void insert(Session session,List<PostModel> postList) throws AddException {
		try {
			for (PostModel postModel : postList) {
				session.save(postModel);
			}
		} catch (Exception e) {
			throw new AddException(e.toString()+"===PostList添加失败");
		}
	}

	public void delete() throws DeleteException {
		
	}

	public void updata(Object obj) throws UpdateException {
		
	}

	public List<Object> select(Object obj) {
		return null;
	}

}
