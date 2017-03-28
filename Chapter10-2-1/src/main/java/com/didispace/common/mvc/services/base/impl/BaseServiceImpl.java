package com.didispace.common.mvc.services.base.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.didispace.common.exception.DaoException;
import com.didispace.common.exception.ServiceException;
import com.didispace.common.exception.SystemException;
import com.didispace.common.mvc.dao.BaseRepository;
import com.didispace.common.mvc.dao.entity.StatusState;
import com.didispace.common.mvc.dao.orm.Page;
import com.didispace.common.mvc.dao.orm.PropertyFilter;
import com.didispace.common.mvc.domain.base.BaseDomain;
import com.didispace.common.mvc.services.base.BaseService;

@Transactional
public abstract class BaseServiceImpl<T, ID extends Serializable> implements BaseService<T, ID> {
	@Autowired
	protected BaseRepository<T, ID> jpaRepository;
	
//	protected HibernateDao<T, ID> entityRepository;
//	protected abstract HibernateDao<T, ID> getEntityDao();
	
	@Override
	@Transactional
	public void save(T entity) {
		jpaRepository.save(entity);
	}

	@Override
	@Transactional
	public T update(T entity) {
		return jpaRepository.save(entity);
	}

	@Override
	@Transactional
	public void delete(T entity) {
		jpaRepository.delete(entity);
	}

	@Override
	public T get(ID id) {
		return jpaRepository.getOne(id);
	}

	@Override
	public List<T> findByProperty(String propertyName, String propertyValue) {
		return jpaRepository.findByProperty(propertyName, propertyValue);
	}

	@Override
	@Transactional(readOnly = true)
	public List<T> findAll() {
		return jpaRepository.findAll();
	}

	@Override
	@Transactional
	public void logicDelete(BaseDomain<ID> entity) {
		jpaRepository.logicDelete(entity);
	}

	@Override
	@Transactional
	public void logicDelete(ID primaryKey) {
		jpaRepository.logicDelete(primaryKey);
	}

	@Override
	@Transactional
	public void delete(ID id) {
		jpaRepository.delete(id);
	}

//	@Override
//	public Page<T> findAll(Specification<T> spe, PageRequest request) {
//		return jpaRepository.findPage(spe, request);
//	}
//
//	@Override
//	public Page<T> findAll(SearchPageRequest<T> searchpage) {
//		return jpaRepository.findPage(searchpage.getCondition(), searchpage);
//	}
//
//	@Override
//	public SearchPageImpl<T> findAll(SearchPageImpl<T> searchpage) {
//		return jpaRepository.findPage(searchpage);
//	}
	
	
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
	@Override
    @Transactional(readOnly = true)
    public Page<T> findPage(Page<T> p,
                         List<PropertyFilter> filters, boolean isFilterDelete)
            throws ServiceException {
        // 过滤逻辑删除的数据
        if (isFilterDelete) {
            PropertyFilter normal = new PropertyFilter("NEI_status",
                    StatusState.delete.getValue() + "");
            filters.add(normal);
        }
        return jpaRepository.findPage(p, filters);
//        return getEntityDao().findPage(p, filters);
    }
	/**
     * 过滤器分页查询.
     *
     * @param page
     *            分页对象
     * @param filters
     *            属性过滤器
     * @return
     * @throws DaoException
     * @throws SystemException
     * @throws ServiceException
     */
    @Transactional(readOnly = true)
    public Page<T> findPage(final Page<T> page, final List<PropertyFilter> filters)
            throws  ServiceException {
        return this.findPage(page, filters,false);
    }
}