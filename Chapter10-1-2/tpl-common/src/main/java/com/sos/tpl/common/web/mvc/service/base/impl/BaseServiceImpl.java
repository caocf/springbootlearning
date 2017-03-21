package com.sos.tpl.common.web.mvc.service.base.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import com.sos.tpl.common.web.mvc.dao.BaseRepository;
import com.sos.tpl.common.web.mvc.domain.SearchPageImpl;
import com.sos.tpl.common.web.mvc.domain.SearchPageRequest;
import com.sos.tpl.common.web.mvc.domain.base.BaseDomain;
import com.sos.tpl.common.web.mvc.service.base.BaseService;

@Transactional(readOnly=true)
public class BaseServiceImpl<T, ID extends Serializable> implements
		BaseService<T, ID> {

	protected BaseRepository<T, ID> jpaRepository;

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

	@Override
	public Page<T> findAll(Specification<T> spe, PageRequest request) {
		return jpaRepository.findPage(spe, request);
	}

	@Override
	public Page<T> findAll(SearchPageRequest<T> searchpage) {
		return jpaRepository.findPage(searchpage.getCondition(), searchpage);
	}

	@Override
	public SearchPageImpl<T> findAll(SearchPageImpl<T> searchpage) {
		return jpaRepository.findPage(searchpage);
	}

}