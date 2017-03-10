package com.sos.fleet.common.domain;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(VehicleBindingDomain.class)
public abstract class VehicleBindingDomain_ {

	public static volatile SingularAttribute<VehicleBindingDomain, Long> id;
	public static volatile SingularAttribute<VehicleBindingDomain, Long> operatorId;
	public static volatile SingularAttribute<VehicleBindingDomain, Integer> status;
	public static volatile SingularAttribute<VehicleBindingDomain, String> vin;
	public static volatile SingularAttribute<VehicleBindingDomain, Long> fleetId;
	public static volatile SingularAttribute<VehicleBindingDomain, Long> userId;
	public static volatile SingularAttribute<VehicleBindingDomain, Date> bindingDate;
	public static volatile SingularAttribute<VehicleBindingDomain, String> comments;
	public static volatile SingularAttribute<VehicleBindingDomain, UserDomain> operator;
	public static volatile SingularAttribute<VehicleBindingDomain, FleetDomain> fleetDomain;
	public static volatile SingularAttribute<VehicleBindingDomain, UserDomain> userDomain;
	public static volatile SingularAttribute<VehicleBindingDomain, String> plateId;
	public static volatile SingularAttribute<VehicleBindingDomain, String> model;
	public static volatile SingularAttribute<VehicleBindingDomain, String> driver;
	public static volatile SingularAttribute<VehicleBindingDomain, String> telephone;
	public static volatile SingularAttribute<VehicleBindingDomain, VehicleDomain> vehicleDomain;
}

