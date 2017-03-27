package com.sos.fleet.service;

import com.sos.fleet.common.domain.VehicleBindingDomain;
import com.sos.fleet.common.domain.VehicleDomain;
import com.sos.fleet.common.dto.VehicleBindResultDto;

import java.util.List;
import java.util.Map;

public interface VehicleBindingService extends BaseService<VehicleBindingDomain, Long>{

//	VehicleBindingDomain getBindVehicle(Long id);

	void releaseBind(Long id);

	List<VehicleBindResultDto> bindVehicle(List<VehicleBindingDomain> vehiclebinds, Long userId);

	void updateVehicleBind(VehicleBindingDomain vehiclebind);

}
