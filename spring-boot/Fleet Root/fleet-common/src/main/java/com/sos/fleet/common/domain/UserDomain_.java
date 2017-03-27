package com.sos.fleet.common.domain;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(UserDomain.class)
public abstract class UserDomain_ extends com.sos.fleet.common.domain.base.BaseDomain_ {

	public static volatile SingularAttribute<UserDomain, String> orgId;
	public static volatile SingularAttribute<UserDomain, Long> id;
	public static volatile SingularAttribute<UserDomain, String> address;
	public static volatile SingularAttribute<UserDomain, String> email;
	public static volatile SingularAttribute<UserDomain, String> userName;
	public static volatile SingularAttribute<UserDomain, Long> fleetId;
	public static volatile SingularAttribute<UserDomain, String> mobile;
	public static volatile SingularAttribute<UserDomain, FleetDomain> fleetDomain;

}

