package com.sos.tpl.common.web.mvc.service.ldap;

import java.io.Serializable;

public interface BaseLdapService<T, ID extends Serializable>
		{
	/**
	 * @param entity
	 * 			Domain object.
	 * @throws AppException 
	 */
	T save(T entity) ;
	/**
	 * @param entity
	 * 			Domain object.
	 */
	T update(T entity);
	/**
	 * @param entity
	 * 			Domain object.
	 */
	void delete(T entity);
	/**
	 * @param id
	 * 			Primary key of the domain class.
	 * @return Domain object.
	 */
	T get(ID id);
	
	/**
	 * @Author      :      JUNJZHU
	 * @Date        :      2013-1-15
	 * @param password
	 * @param rdn
	 * @return
	 */
	boolean validatePassword(String password, String uid);
	void logicDelete(ID id);
}
