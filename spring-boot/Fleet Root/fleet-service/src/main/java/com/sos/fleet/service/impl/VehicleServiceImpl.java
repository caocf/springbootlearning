package com.sos.fleet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sos.fleet.common.domain.VehicleDomain;
import com.sos.fleet.data.dao.VehicleDao;
import com.sos.fleet.service.VehicleService;
@Service
@Transactional(readOnly=true,rollbackFor = RuntimeException.class)
public class VehicleServiceImpl extends BaseServiceImpl<VehicleDomain, Long> implements VehicleService {

	private VehicleDao vehicleDao;

	@Autowired
	public void setVehicleDao(VehicleDao vehicleDao) {
		this.jpaRepository = this.vehicleDao = vehicleDao;
	}

}
