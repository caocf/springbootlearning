package com.didispace.module.sys.service;

import java.util.List;

import com.didispace.common.exception.DaoException;
import com.didispace.common.exception.ServiceException;
import com.didispace.common.exception.SystemException;
import com.didispace.common.mvc.dao.orm.Page;

public interface RoleService  {
	
//	public List<UserDomain> getUserbyName(String userName);
//	
//	public List<UserDomain> getUsers();
	
	public Page findPage(Page p, List filters)
			throws DaoException, SystemException, ServiceException ;
}
