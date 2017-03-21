package com.sos.tpl.common.web.mvc.service.ldap.impl;

import java.io.Serializable;

import com.sos.tpl.common.web.mvc.dao.ldap.BaseLdapDao;
import com.sos.tpl.common.web.mvc.service.ldap.BaseLdapService;

public class BaseLdapServiceImpl<T,ID extends Serializable> implements BaseLdapService<T, ID>{

	protected BaseLdapDao<T, ID> baseDao;
	
	@Override
	public T save(T entity) {
		return baseDao.save(entity);
	}

	@Override
	public T update(T entity) {
		return	baseDao.update(entity);		
	}

	@Override
	public void delete(T entity) {
		baseDao.delete(entity);		
	}
	@Override
	public void logicDelete(ID id) {
		baseDao.logicDelete(id);		
	}
	@Override
	public T get(ID id) {
		return baseDao.get(id);
	}

	@Override
	public boolean validatePassword(String password, String uid) {
		return baseDao.validatePassword(password, uid);
	}


}