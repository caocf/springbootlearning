package com.sos.portal.scheduler.data;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

public class SchedulerRepositoryFactoryBean<T  extends Repository<S, ID>,S,ID extends Serializable> extends JpaRepositoryFactoryBean<T,S,ID>{

	@Override
	protected RepositoryFactorySupport createRepositoryFactory(
			EntityManager entityManager) {
		return new SchedulerRepositoryFactory<T,ID>(entityManager);
	}

}
