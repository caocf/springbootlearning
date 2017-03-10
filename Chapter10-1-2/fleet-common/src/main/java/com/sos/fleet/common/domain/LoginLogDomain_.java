package com.sos.fleet.common.domain;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(LoginLogDomain.class)
public abstract class LoginLogDomain_ {

	public static volatile SingularAttribute<LoginLogDomain, Long> id;
	public static volatile SingularAttribute<LoginLogDomain, Date> loginTime;
	public static volatile SingularAttribute<LoginLogDomain, UserDomain> userDomain;
	public static volatile SingularAttribute<LoginLogDomain, Long> userId;
	public static volatile SingularAttribute<LoginLogDomain, String> roleType;
	public static volatile SingularAttribute<LoginLogDomain, String> ipAddress;

}

