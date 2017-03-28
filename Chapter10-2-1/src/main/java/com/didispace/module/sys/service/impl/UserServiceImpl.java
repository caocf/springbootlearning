package com.didispace.module.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.didispace.module.sys.dao.UserRepository;
import com.didispace.module.sys.domain.UserDomain;
import com.didispace.module.sys.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@PersistenceContext
	private EntityManager em;
	
	public List<UserDomain> getUserbyName(String userName){
		List<UserDomain> users = new ArrayList<UserDomain>();
		String sqlStr="select * from UserDomain where userName='"+userName+"'";
		users = em.createNativeQuery(sqlStr,UserDomain.class).getResultList();
		if(users!=null && !users.isEmpty()){
			for(UserDomain user:users){
				System.out.println("username:"+user.getUserName()+";useremail:"+user.getEmail());
			}
		}
		return users;
	}
	
	public List<UserDomain> getUsers(){
		List<UserDomain> users = userRepository.findAll();
		return users;
	}
	
}
