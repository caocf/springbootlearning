package com.didispace.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.didispace.dao.UserRepository;
import com.didispace.domain.User;
import com.didispace.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@PersistenceContext
	private EntityManager em;
	
	public List<User> getUserbyName(String username){
		List<User> users = new ArrayList<User>();
		String sqlStr="select * from User where name='"+username+"'";
		users = em.createNativeQuery(sqlStr,User.class).getResultList();
		if(users!=null && !users.isEmpty()){
			for(User user:users){
				System.out.println("username:"+user.getName()+";userage:"+user.getAge());
			}
		}
		return users;
	}
	
	
	
}
