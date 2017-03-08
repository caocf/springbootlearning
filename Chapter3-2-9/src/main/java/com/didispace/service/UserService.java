package com.didispace.service;

import java.util.List;

import com.didispace.domain.User;

public interface UserService {
	
	public List<User> getUserbyName(String username);
}
