package com.sos.fleet.common.domain;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(VehicleDomain.class)
public abstract class VehicleDomain_ extends com.sos.fleet.common.domain.base.BaseDomain_ {

	public static volatile SingularAttribute<VehicleDomain, Long> id;
	public static volatile SingularAttribute<VehicleDomain, String> plateId;
	public static volatile SingularAttribute<VehicleDomain, String> model;
	public static volatile SingularAttribute<VehicleDomain, String> vin;
	public static volatile SingularAttribute<VehicleDomain, String> driver;
	public static volatile SingularAttribute<VehicleDomain, String> telephone;
	public static volatile SetAttribute<VehicleDomain, VehicleBindingDomain> vehicleBindingDomainsForVin;

}

