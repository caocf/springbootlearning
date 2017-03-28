package com.didispace.common.mvc.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.didispace.common.mvc.dao.orm.Page;
import com.didispace.common.mvc.dao.orm.PropertyFilter;
import com.didispace.common.mvc.domain.base.BaseDomain;
@NoRepositoryBean
public abstract interface BaseRepository<T,ID extends Serializable> extends JpaRepository<T, ID> {

	void logicDelete(ID id);
	void logicDelete(BaseDomain<ID> entity); 
	void logicDelete(Iterable<? extends BaseDomain<ID>> entities);
	void logicDeleteInBatch(Iterable<BaseDomain<ID>> entities);
	void logicDeleteInBatchById(Iterable<ID> entities);
	List<T> findByProperty(String propertyName, Object propertyValue);
//	<C extends Specification<T>> Page<T> findPage(SearchPageRequest<T> pageRequest);
//	SearchPageImpl<T> findPage(SearchPageImpl<T> pageRequest);
//	public Page<T> findPage(Specification<T> spe, PageRequest request);
//	public List<T> findAll(Specification<T> spec, Sort sort);
	
	/**
	 * 按属性过滤条件列表分页查找对象.
	 */
	public Page<T> findPage(final Page<T> page,
			final List<PropertyFilter> filters);
}
