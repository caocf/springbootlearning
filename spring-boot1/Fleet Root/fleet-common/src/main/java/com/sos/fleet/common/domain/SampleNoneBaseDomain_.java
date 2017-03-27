package com.sos.fleet.common.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SampleNoneBaseDomain.class)
public abstract class SampleNoneBaseDomain_ {

	public static volatile SingularAttribute<SampleNoneBaseDomain, Long> id;
	public static volatile SingularAttribute<SampleNoneBaseDomain, Integer> age;
	public static volatile SingularAttribute<SampleNoneBaseDomain, String> name;
	public static volatile SingularAttribute<SampleNoneBaseDomain, String> comments;

}

