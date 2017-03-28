package com.didispace.common.mvc.dao;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.didispace.common.mvc.domain.SearchPageImpl;
import com.didispace.common.mvc.domain.SearchPageRequest;
import com.didispace.common.mvc.domain.base.BaseDomain;
@Transactional(readOnly = true)
public class BaseRepositoryImpl<T, ID extends Serializable> extends
		SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {
	private final EntityManager entityManager;
	private final JpaEntityInformation<T, ?> entityInformation;
	private final String deleteFlag = "deleteFlag";
	private final Integer deleteStatus = 1;
	private final String id = "id";
	private final String logicDeleteQuery = "update %s x  set x.deleteFlag = ?1  where ";

	public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
		this(JpaEntityInformationSupport.getEntityInformation(domainClass, em), em);
	}

	public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation,
			EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityInformation = entityInformation;
		this.entityManager = entityManager;
	}

	@Override
	@Transactional
	public void logicDelete(ID id) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaUpdate<T> criteriaUpdate = builder
				.createCriteriaUpdate(entityInformation.getJavaType());
		Root<T> geekRoot = criteriaUpdate.from(entityInformation.getJavaType());
		criteriaUpdate.set(deleteFlag, deleteStatus).where(
				builder.equal(geekRoot.get(this.id), id));
		entityManager.createQuery(criteriaUpdate).executeUpdate();
	}

	@Override
	@Transactional
	public void logicDelete(BaseDomain<ID> entity) {
		if (!entityManager.contains(entity)) {
			entityManager.merge(entity);
		}
		logicDelete(entity.getId());
	}

	@Override
	@Transactional
	public void logicDelete(Iterable<? extends BaseDomain<ID>> entities) {
		for (BaseDomain<ID> entity : entities) {
			logicDelete(entity);
		}
	}

	@Override
	@Transactional
	public void logicDeleteInBatch(Iterable<BaseDomain<ID>> entities) {
		Iterator<? extends BaseDomain<ID>> it = entities.iterator();
		if (!it.hasNext()) {
			return;
		}
		StringBuilder sb = new StringBuilder(0);
		for (int i = 0; it.hasNext(); i++) {
			if (i == 0) {
				sb.append(String.format(logicDeleteQuery,
						entityInformation.getEntityName()));
			}
			sb.append(id).append("=?").append(i + 2);
			if (it.hasNext()) {
				sb.append(" or ");
			}
		}
		it = entities.iterator();
		Query query = entityManager.createQuery(sb.toString());
		for (int i = 0; it.hasNext(); i++) {
			query.setParameter(i + 1, it.next().getId());
		}
		query.executeUpdate();
	}

	@Override
	@Transactional
	public void logicDeleteInBatchById(Iterable<ID> entities) {
		Iterator<ID> it = entities.iterator();
		if (!it.hasNext()) {
			return;
		}
		StringBuilder sb = new StringBuilder(0);
		for (int i = 0; it.hasNext(); i++) {
			if (i == 0) {
				sb.append(String.format(logicDeleteQuery,
						entityInformation.getEntityName()));
			}
			sb.append(id).append("=?").append(i + 2);
			if (it.hasNext()) {
				sb.append(" or ");
			}
		}
		it = entities.iterator();
		Query query = entityManager.createQuery(sb.toString());
		for (int i = 0; it.hasNext(); i++) {
			query.setParameter(i + 1, it.next());
		}
		query.executeUpdate();
	}

	@Override
	public List<T> findByProperty(String propertyName, Object propertyValue) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = builder.createQuery(entityInformation
				.getJavaType());
		Root<T> root = criteriaQuery.from(entityInformation.getJavaType());
		criteriaQuery
				.where(builder.equal(root.get(propertyName), propertyValue));
		return entityManager.createQuery(criteriaQuery).getResultList();
	}
	@Override
	public <C extends Specification<T>> Page<T> findPage(
			SearchPageRequest<T> pageRequest) {
		return findAll(pageRequest.getCondition(), pageRequest);
	}

	@Override
	public Page<T> findPage(Specification<T> spe, PageRequest request) {
		return findAll(spe, request);
	}

	@Override
	public SearchPageImpl<T> findPage(SearchPageImpl<T> pageRequest) {
		pageRequest.setPageResult(findAll(pageRequest.getCondition(), pageRequest));
		return pageRequest;
	}
}