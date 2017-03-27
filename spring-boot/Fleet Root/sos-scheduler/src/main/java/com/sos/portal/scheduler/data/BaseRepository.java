package com.sos.portal.scheduler.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.sos.portal.scheduler.domain.BaseDomain;
import com.sos.portal.scheduler.domain.SearchPageImpl;
import com.sos.portal.scheduler.domain.SearchPageRequest;

@NoRepositoryBean
public abstract interface BaseRepository	<T,ID extends Serializable> extends JpaRepository<T, ID> {

	void logicDelete(ID id);
	void logicDelete(BaseDomain<ID> entity); 
	void logicDelete(Iterable<? extends BaseDomain<ID>> entities);
	void logicDeleteInBatch(Iterable<BaseDomain<ID>> entities);
	void logicDeleteInBatchById(Iterable<ID> entities);
	List<T> findByProperty(String propertyName, Object propertyValue);
	<C extends Specification<T>> Page<T> findPage(SearchPageRequest<T> pageRequest);
	SearchPageImpl<T> findPage(SearchPageImpl<T> pageRequest);
	public Page<T> findPage(Specification<T> spe, PageRequest request);
	public List<T> findAll(Specification<T> spec, Sort sort);
	public  EntityManager getEntityManager();
	public void executeSql(String ... sql);
}
