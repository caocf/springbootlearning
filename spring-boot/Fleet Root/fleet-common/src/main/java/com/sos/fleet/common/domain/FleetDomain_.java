package com.sos.fleet.common.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(FleetDomain.class)
public abstract class FleetDomain_ extends com.sos.fleet.common.domain.base.BaseDomain_ {

	public static volatile SingularAttribute<FleetDomain, Long> id;
	public static volatile SingularAttribute<FleetDomain, String> name;
	public static volatile SingularAttribute<FleetDomain, String> fleetType;

}

