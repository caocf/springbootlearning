package com.sos.fleet.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sos.fleet.common.condition.VehicleCondition;
import com.sos.fleet.common.constants.OperationKeys;
import com.sos.fleet.common.constants.OperationTypeKeys;
import com.sos.fleet.common.constants.StatusKeys;
import com.sos.fleet.common.domain.SearchPageImpl;
import com.sos.fleet.common.domain.SearchPageImpl.SimpleOrder;
import com.sos.fleet.common.domain.VehicleDomain;
import com.sos.fleet.common.settings.PageSettings;
import com.sos.fleet.common.settings.SecuritySettings;
import com.sos.fleet.common.util.OperationUtil;
import com.sos.fleet.common.util.SecurityUtil;
import com.sos.fleet.service.VehicleService;

@Controller
@Secured(SecuritySettings.ROLE_USER)
public class VehicleController{
	private static final int SIZE=8;
	private static Log log = LogFactory.getLog(VehicleController.class);
	@Autowired
	private VehicleService vehicleService;
	@RequestMapping({"showVehicle"})
	public ModelAndView showVehicle(SearchPageImpl<VehicleDomain> searchPageImpl,VehicleCondition vehicleCondition){
		Long userId = SecurityUtil.getUserId();
		if(userId==null){
			throw new AccessDeniedException("the userId is empty.");
		}
		vehicleCondition.setUserId(userId);
		ModelAndView mav = new ModelAndView("vehicle/showVehicle");
		vehicleCondition.setStatus(StatusKeys.SUCCESS);
		searchPageImpl.setCondition(vehicleCondition);
		searchPageImpl.setSize(SIZE);
		if(CollectionUtils.isEmpty(searchPageImpl.getOrders())){
			List<SimpleOrder> orders = new ArrayList<SimpleOrder>(1);
			orders.add(new SimpleOrder(Direction.DESC,"vehicleBindingDomainsForVin.bindingDate"));
			searchPageImpl.setOrders(orders );
		}
		vehicleService.findAll(searchPageImpl);
		mav.addObject(PageSettings.ATTRIBUTE_KEY, searchPageImpl);
		OperationUtil.log(OperationKeys.SHOW_VECHILE,OperationTypeKeys.SHOW_VEHICLE);
		return mav;
	}
}
