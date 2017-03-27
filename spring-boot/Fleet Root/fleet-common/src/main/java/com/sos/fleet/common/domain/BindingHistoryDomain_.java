package com.sos.fleet.common.domain;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import com.sos.fleet.common.util.RegexUtil;

//@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(BindingHistoryDomain.class)
public abstract class BindingHistoryDomain_ {

	public static volatile SingularAttribute<BindingHistoryDomain, Long> vehicleId;
	public static volatile SingularAttribute<BindingHistoryDomain, Long> operatorId;
	public static volatile SingularAttribute<BindingHistoryDomain, Integer> status;
	public static volatile SingularAttribute<BindingHistoryDomain, Date> bindingStartDate;
	public static volatile SingularAttribute<BindingHistoryDomain, UserDomain> operator;
	public static volatile SingularAttribute<BindingHistoryDomain, Long> id;
	public static volatile SingularAttribute<BindingHistoryDomain, VehicleBindingDomain> vehicleBindingDomain;
	public static volatile SingularAttribute<BindingHistoryDomain, Long> bindingId;
	public static volatile SingularAttribute<BindingHistoryDomain, Date> bindingEndDate;
	public static volatile SingularAttribute<BindingHistoryDomain, Long> fleetId;
	public static volatile SingularAttribute<BindingHistoryDomain, Long> userId;
	public static volatile SingularAttribute<BindingHistoryDomain, String> comments;
	public static volatile SingularAttribute<BindingHistoryDomain, FleetDomain> fleetDomain;
	public static volatile SingularAttribute<BindingHistoryDomain, UserDomain> userDomain;
	public static volatile SingularAttribute<BindingHistoryDomain, String> plateId;
	public static volatile SingularAttribute<BindingHistoryDomain, String> model;
	public static volatile SingularAttribute<BindingHistoryDomain, String> vin;
	public static volatile SingularAttribute<BindingHistoryDomain, String> driver;
	public static volatile SingularAttribute<BindingHistoryDomain, String> telephone;
}

