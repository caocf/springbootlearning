package com.sos.fleet.common.domain;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(LocationHistory.class)
public abstract class LocationHistory_ {

	public static volatile SingularAttribute<LocationHistory, Long> id;
	public static volatile SingularAttribute<LocationHistory, Date> locatedTime;
	public static volatile SingularAttribute<LocationHistory, String> vin;
	public static volatile SingularAttribute<LocationHistory, String> longitude;
	public static volatile SingularAttribute<LocationHistory, String> latitude;
	public static volatile SingularAttribute<LocationHistory, Date> createDate;

}

