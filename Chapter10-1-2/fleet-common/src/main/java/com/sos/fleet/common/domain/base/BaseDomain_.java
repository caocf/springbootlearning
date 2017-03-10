package com.sos.fleet.common.domain.base;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.sos.fleet.common.domain.UserDomain;

//@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BaseDomain.class)
public abstract class BaseDomain_ {

	public static volatile SingularAttribute<BaseDomain<Long>, Long> id;
	public static volatile SingularAttribute<BaseDomain<Long>, Long> lastUpdateBy;
	public static volatile SingularAttribute<BaseDomain<Long>, UserDomain> userDomainLastUpdateBy;
	public static volatile SingularAttribute<BaseDomain<Long>, Boolean> deleteFlag;
	public static volatile SingularAttribute<BaseDomain<Long>, Date> createDate;
	public static volatile SingularAttribute<BaseDomain<Long>, Long> createBy;
	public static volatile SingularAttribute<BaseDomain<Long>, UserDomain> userDomainByCreateBy;
	public static volatile SingularAttribute<BaseDomain<Long>, Date> lastUpdateDate;

}

