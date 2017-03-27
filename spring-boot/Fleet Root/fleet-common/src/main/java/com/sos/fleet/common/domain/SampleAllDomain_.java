package com.sos.fleet.common.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SampleAllDomain.class)
public abstract class SampleAllDomain_ extends com.sos.fleet.common.domain.base.BaseDomain_ {

	public static volatile SingularAttribute<SampleAllDomain, Long> id;
	public static volatile SingularAttribute<SampleAllDomain, Integer> age;
	public static volatile SingularAttribute<SampleAllDomain, String> name;
	public static volatile SingularAttribute<SampleAllDomain, String> comments;

}

