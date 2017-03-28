package com.didispace.common.mvc.services.base;

import java.io.Serializable;
import java.util.List;

import com.didispace.common.exception.DaoException;
import com.didispace.common.exception.ServiceException;
import com.didispace.common.exception.SystemException;
import com.didispace.common.mvc.dao.orm.Page;
import com.didispace.common.mvc.dao.orm.PropertyFilter;
import com.didispace.common.mvc.domain.base.BaseDomain;

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
	
//	public Page<T> findAll(Specification<T> spe, PageRequest request);
//	public Page<T> findAll(SearchPageRequest<T> searchpage);
//	public SearchPageImpl<T> findAll(SearchPageImpl<T> searchpage);
	
	/**
     * 过滤器分页查询.
     * @param p 分页对象
     * @param filters
     *            属性过滤器
     * @param isFilterDelete
     *            是否过滤逻辑删除的数据
     * @return
     * @throws DaoException
     * @throws SystemException
     * @throws ServiceException
     */
	public Page<T> findPage(Page<T> p,
            List<PropertyFilter> filters, boolean isFilterDelete)
            		throws   ServiceException;
	
	
	public Page<T> findPage(final Page<T> page, final List<PropertyFilter> filters)
            throws  ServiceException;
}