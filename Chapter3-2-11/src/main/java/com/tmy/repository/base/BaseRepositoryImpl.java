package com.tmy.repository.base;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class BaseRepositoryImpl<T, ID extends Serializable> 
    extends SimpleJpaRepository<T, ID> 
    implements BaseRepository<T, ID> {

    private final Class<T> domainClass;

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
        super(domainClass, entityManager);
        this.domainClass = domainClass;
    }

    @Override
    public boolean support(String modelType) {
        return domainClass.getName().equals(modelType);
    }

}
