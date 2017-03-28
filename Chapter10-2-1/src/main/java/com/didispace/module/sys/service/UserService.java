package com.didispace.module.sys.service;

import java.util.List;

import com.didispace.module.sys.domain.UserDomain;

public interface UserService {
	
	public List<UserDomain> getUserbyName(String userName);
	
	public List<UserDomain> getUsers();
}
