package com.didispace.common.mvc.dao;

import static org.springframework.data.querydsl.QueryDslUtils.QUERY_DSL_PRESENT;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;

public  class BaseRepositoryFactory<T, ID extends Serializable>
    extends JpaRepositoryFactory {

    private final EntityManager entityManager;

    public BaseRepositoryFactory(EntityManager em) {
      super(em);
      this.entityManager = em;
    }

    @Override
	protected Object getTargetRepository(RepositoryInformation information) {
    	SimpleJpaRepository<?, ?> repository = getTargetRepository(information, entityManager);
		return repository;
	}

	@Override
	protected <T, ID extends Serializable> SimpleJpaRepository<?, ?> getTargetRepository(
			RepositoryInformation information, EntityManager entityManager) {
		// TODO Auto-generated method stub
//		return super.getTargetRepository(information, entityManager);
		Class<?> repositoryInterface = information.getRepositoryInterface();
		@SuppressWarnings("unchecked")
		JpaEntityInformation<T, ID> entityInformation = (JpaEntityInformation<T, ID>) getEntityInformation(information.getDomainType());
		
		SimpleJpaRepository<T, ID> repo = isQueryDslExecutor(repositoryInterface) ? 
				new QueryDslJpaRepository<T,ID>(entityInformation, entityManager) 
			: new BaseRepositoryImpl<T, ID>(entityInformation, entityManager);
		return repo;
	}

	protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
    	if (isQueryDslExecutor(metadata.getRepositoryInterface())) {
			return QueryDslJpaRepository.class;
		} else {
			return BaseRepositoryImpl.class;
		}
    }
	
	private boolean isQueryDslExecutor(Class<?> repositoryInterface) {
		return QUERY_DSL_PRESENT && QueryDslPredicateExecutor.class.isAssignableFrom(repositoryInterface);
	}
  }