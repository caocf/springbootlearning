package com.sos.fleet.data.ldap.base;

import java.io.Serializable;
import java.util.List;

import javax.naming.Name;

import org.springframework.ldap.core.support.BaseLdapNameAware;

public interface BaseLdapDao<T,ID extends Serializable> extends BaseLdapNameAware {
	public T save(T T);
	public T update(T entity);
	public T get(ID id);
	public void delete(T entity);
	public void delete(ID id);
	public void delete(Name rnm);
	public void logicDelete(T entity);
	public void logicDelete(ID id);
	public void logicDelete(Name rnm);
	public List<T> findByProperty(String propertyName, Object propertyValue);
	public T updatePropertiesNonNull(T entity);
	public boolean validatePassword(String password, String uid);
}
