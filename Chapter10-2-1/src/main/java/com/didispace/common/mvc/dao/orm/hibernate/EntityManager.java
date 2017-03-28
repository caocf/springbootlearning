//package com.didispace.common.mvc.dao.orm.hibernate;
//
//import java.io.Serializable;
//import java.util.Collection;
//import java.util.List;
//
//import org.hibernate.criterion.Criterion;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.Assert;
//
//import com.didispace.common.exception.DaoException;
//import com.didispace.common.exception.ServiceException;
//import com.didispace.common.exception.SystemException;
//import com.didispace.common.mvc.dao.entity.StatusState;
//import com.didispace.common.mvc.dao.orm.Page;
//import com.didispace.common.mvc.dao.orm.PropertyFilter;
//import com.didispace.common.mvc.dao.orm.PropertyFilter.MatchType;
//import com.didispace.common.mvc.services.base.SimpleBaseService;
//import com.didispace.common.utils.collections.Collections3;
//
///**
// * Service层领域对象业务管理类基类.
// * 使用HibernateDao<T,PK>进行业务对象的CRUD操作,子类需重载getEntityDao()函数提供该DAO.
// * 
// * @param <T>
// *            领域对象类型
// * @param <PK>
// *            领域对象的主键类型
// * 
// *            eg. public class UserManager extends EntityManager<User, Long>{ }
// * 
// */
//@Transactional
//public abstract class EntityManager<T, ID extends Serializable> implements SimpleBaseService<T, ID> {
//
//	protected Logger logger = LoggerFactory.getLogger(getClass());
//
//	protected abstract HibernateDao<T, ID> getEntityDao();
//
//	// CRUD函数 //
//
//	/* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#save(T)
//	 */
//	@Override
//	public void save(final T entity) throws DaoException, SystemException,
//			ServiceException {
//		getEntityDao().save(entity);
//	}
//
//	/* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#update(T)
//	 */
//	@Override
//	public T update(final T entity) throws DaoException, SystemException,
//			ServiceException {
//		getEntityDao().update(entity);
//		return entity;
//	}
//
//    /* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#saveEntity(T)
//	 */
//    @Override
//	public void saveEntity(final T entity) throws DaoException,
//            SystemException, ServiceException {
//        getEntityDao().saveEntity(entity);
//    }
//
//	/* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#saveOrUpdate(T)
//	 */
//	@Override
//	public void saveOrUpdate(final T entity) throws DaoException,
//			SystemException, ServiceException {
//		getEntityDao().saveOrUpdate(entity);
//	}
//
//	/* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#saveOrUpdate(java.util.Collection)
//	 */
//	@Override
//	public void saveOrUpdate(final Collection<T> entitys) throws DaoException,
//			SystemException, ServiceException {
//		getEntityDao().saveOrUpdate(entitys);
//	}
//	
//	/* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#clear()
//	 */
//	@Override
//	public void clear() throws DaoException,
//		    SystemException, ServiceException {
//	    getEntityDao().clear();
//	}
//	
//	/* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#evict(T)
//	 */
//	@Override
//	public void evict(T entity) throws DaoException,
//		    SystemException, ServiceException {
//		getEntityDao().evict(entity);
//	}
//
//    /* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#merge(T)
//	 */
//	@Override
//	public void merge(final T entity) throws DaoException, SystemException,
//			ServiceException {
//		getEntityDao().merge(entity);
//	}
//
//	/* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#deleteById(PK)
//	 */
//	@Override
//	public void deleteById(final ID id) throws DaoException, SystemException,
//			ServiceException {
//		getEntityDao().delete(id);
//	}
//
//	/* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#delete(T)
//	 */
//	@Override
//	public void delete(final T entity) throws DaoException, SystemException,
//			ServiceException {
//		Assert.notNull(entity, "参数[entity]为空!");
//		getEntityDao().delete(entity);
//	}
//
//	/* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#deleteAll(java.util.Collection)
//	 */
//	@Override
//	public void deleteAll(final Collection<T> entitys) throws DaoException,
//			SystemException, ServiceException {
//		if (!Collections3.isEmpty(entitys)) {
//			for (T entity : entitys) {
//				getEntityDao().delete(entity);
//			}
//		} else {
//			logger.warn("参数[entitys]为空.");
//		}
//	}
//
//	/* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#deleteByIds(java.util.List)
//	 */
//	@Override
//	@SuppressWarnings("unchecked")
//	public void deleteByIds(final List<ID> ids) throws DaoException,
//			SystemException, ServiceException {
//		if (!Collections3.isEmpty(ids)) {
//			for (ID id : ids) {
//				getEntityDao().delete(id);
//			}
//		} else {
//			logger.warn("参数[ids]为空.");
//		}
//	}
//
//	/* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#loadById(PK)
//	 */
//	@Override
//	@Transactional(readOnly = true)
//	public T loadById(final ID id) throws DaoException, SystemException,
//			ServiceException {
//		return getEntityDao().load(id);
//	}
//
//	/* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#getById(PK)
//	 */
//	@Override
//	@Transactional(readOnly = true)
//	public T getById(final ID id) throws DaoException, SystemException,
//			ServiceException {
//		return getEntityDao().get(id);
//	}
//
//	/* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#findBy(java.lang.String, java.lang.Object)
//	 */
//	@Override
//	@Transactional(readOnly = true)
//	public List<T> findBy(String propertyName, Object propertyValue)
//			throws DaoException, SystemException, ServiceException {
//		Assert.hasText(propertyName, "参数[entityName]为空!");
//		return getEntityDao().findBy(propertyName, propertyValue);
//	}
//
//	/* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#findUniqueBy(java.lang.String, java.lang.Object)
//	 */
//	@Override
//	@Transactional(readOnly = true)
//	public T findUniqueBy(String propertyName, Object value)
//			throws DaoException, SystemException, ServiceException {
//		return getEntityDao().findUniqueBy(propertyName, value);
//	}
//
//	/* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#findByLike(java.lang.String, java.lang.String)
//	 */
//	@Override
//	@Transactional(readOnly = true)
//	public List<T> findByLike(String propertyName, String value)
//			throws DaoException, SystemException, ServiceException {
//		return getEntityDao().findBy(propertyName, value, MatchType.LIKE);
//	}
//
//	/* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#findPage(com.didispace.common.mvc.dao.orm.Page)
//	 */
//	@Override
//	@Transactional(readOnly = true)
//	public Page<T> findPage(final Page<T> page) throws DaoException,
//			SystemException, ServiceException {
//		return getEntityDao().getAll(page);
//	}
//
//	/* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#getAll()
//	 */
//	@Override
//	@Transactional(readOnly = true)
//	public List<T> getAll() throws DaoException, SystemException,
//			ServiceException {
//		return getEntityDao().getAll();
//	}
//
//	/* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#getAll(java.lang.String, java.lang.String)
//	 */
//	@Override
//	public List<T> getAll(String orderBy, String order) throws DaoException,
//			SystemException, ServiceException {
//		return getEntityDao().getAll(orderBy, order);
//	}
//
//
//	/* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#find(java.util.List)
//	 */
//	@Override
//	@Transactional(readOnly = true)
//	public List<T> find(final List<PropertyFilter> filters)
//			throws DaoException, SystemException, ServiceException {
//		return getEntityDao().find(filters);
//	}
//
//	/* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#find(java.util.List, java.lang.String, java.lang.String)
//	 */
//	@Override
//	@Transactional(readOnly = true)
//	public List<T> find(final List<PropertyFilter> filters,
//			final String orderBy, final String order) throws DaoException,
//			SystemException, ServiceException {
//		return getEntityDao().find(filters, orderBy, order);
//	}
//
//	/* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#findPage(com.didispace.common.mvc.dao.orm.Page, java.lang.String, java.lang.Object)
//	 */
//	@Override
//	@Transactional(readOnly = true)
//	public Page<T> findPage(final Page<T> page, final String hql,
//			final Object... values) throws DaoException, SystemException,
//			ServiceException {
//		return getEntityDao().findPage(page, hql, values);
//	}
//
//    /* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#findPage(com.didispace.common.mvc.dao.orm.Page, java.lang.String, com.didispace.common.mvc.dao.orm.hibernate.Parameter)
//	 */
//    @Override
//	@Transactional(readOnly = true)
//    public Page<T> findPage(final Page<T> page, final String hql,
//                        final Parameter parameter) throws DaoException, SystemException,
//            ServiceException {
//        return getEntityDao().findPage(page, hql, parameter);
//    }
//
//    /* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#findPage(com.didispace.common.mvc.dao.orm.Page, java.util.List)
//	 */
//    @Override
//	@Transactional(readOnly = true)
//    public Page<T> findPage(final Page<T> page, final List<PropertyFilter> filters)
//            throws DaoException, SystemException, ServiceException {
//        return this.findPage(page, filters,false);
//    }
//
//    /* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#findPage(com.didispace.common.mvc.dao.orm.Page, java.util.List, boolean)
//	 */
//    @Override
//	@Transactional(readOnly = true)
//    public Page<T> findPage(Page<T> p,
//                         List<PropertyFilter> filters, boolean isFilterDelete)
//            throws DaoException, SystemException, ServiceException {
//        // 过滤逻辑删除的数据
//        if (isFilterDelete) {
//            PropertyFilter normal = new PropertyFilter("NEI_status",
//                    StatusState.delete.getValue() + "");
//            filters.add(normal);
//        }
//        return getEntityDao().findPage(p, filters);
//    }
//
//
//	/* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#findPageByCriteria(com.didispace.common.mvc.dao.orm.Page, org.hibernate.criterion.Criterion)
//	 */
//	@Override
//	@Transactional(readOnly = true)
//	public Page<T> findPageByCriteria(Page<T> page, Criterion... criterions)
//			throws DaoException, SystemException, ServiceException {
//		Assert.notNull(page, "参数[page]为空!");
//		Assert.notNull(criterions, "参数[criterions]为空!");
//		return getEntityDao().findPage(page, criterions);
//	}
//
//	/* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#findByCriteria(org.hibernate.criterion.Criterion)
//	 */
//	@Override
//	@Transactional(readOnly = true)
//	public List<T> findByCriteria(Criterion... criterions) throws DaoException,
//			SystemException, ServiceException {
//		Assert.notNull(criterions, "参数[criterions]为空!");
//		return getEntityDao().find(criterions);
//	}
//
//	/* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#isUnique(T, java.lang.String)
//	 */
//	@Override
//	@Transactional(readOnly = true)
//	public boolean isUnique(T entity, String uniquePropertyNames)
//			throws DaoException, SystemException, ServiceException {
//		return getEntityDao().isUnique(entity, uniquePropertyNames);
//	}
//
//	/* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#getCriterionsByFilter(java.util.List)
//	 */
//	@Override
//	@Transactional(readOnly = true)
//	public Criterion[] getCriterionsByFilter(List<PropertyFilter> filters)
//			throws DaoException, SystemException, ServiceException {
//		return getEntityDao().buildCriterionByPropertyFilter(filters);
//	}
//
//
//    /* (non-Javadoc)
//	 * @see com.didispace.common.mvc.dao.orm.hibernate.SimpleBaseServiceImpl#initProxyObject(java.lang.Object)
//	 */
//    @Override
//	@Transactional(readOnly = true)
//    public void initProxyObject(Object proxy) {
//        getEntityDao().initProxyObject(proxy);
//    }
//}