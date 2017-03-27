package com.sos.portal.scheduler.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import com.sos.portal.scheduler.domain.BaseDomain;
import com.sos.portal.scheduler.domain.SearchPageImpl;
import com.sos.portal.scheduler.domain.SearchPageRequest;


public interface BaseService<T,ID extends Serializable> {
	/**
	 * @param entity
	 * 			Domain object.
	 * @throws AppException 
	 */
	void save(T entity);
	/**
	 * @param entity
	 * 			Domain object.
	 * @throws AppException 
	 */
	T update(T entity) ;
	/**
	 * @param entity
	 * 			Domain object.
	 * @throws AppException 
	 */
	void delete(T entity) ;
	
	public void delete(ID id) ;
	
	/**
	 * @param id
	 * 			Primary key of the domain class.
	 * @return Domain object.
	 */
	T get(ID id);
	/**
	 * @param propertyName
	 * 			Property name of the domain class.
	 * @param propertyValue
	 * 			Property value of the domain object.
	 * @return 
	 */
	List<T> findByProperty(String propertyName,String propertyValue);
	
	/**
	 * @Author      :      JUNJZHU
	 * @Date        :      2012-11-21
	 * @return
	 */
	List<T> findAll();
	
	/**
	 * @Author      :      JUNJZHU
	 * @Date        :      2012-12-25
	 * @param entity
	 * @throws Exception 
	 */
	public void logicDelete(BaseDomain<ID> entity);
	
	/**
	 * @Author      :      JUNJZHU
	 * @Date        :      2012-12-25
	 * @param primaryKey
	 * @throws Exception 
	 */
	public void logicDelete(ID primaryKey);
	
	public Page<T> findAll(Specification<T> spe, PageRequest request);
	public Page<T> findAll(SearchPageRequest<T> searchpage);
	public SearchPageImpl<T> findAll(SearchPageImpl<T> searchpage);
}
