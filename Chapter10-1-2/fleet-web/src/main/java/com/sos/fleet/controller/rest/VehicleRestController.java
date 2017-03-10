//package com.sos.fleet.controller.rest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.data.domain.Sort.Direction;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.util.CollectionUtils;
//import org.springframework.util.NumberUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.sos.fleet.common.condition.VehicleCondition;
//import com.sos.fleet.common.constants.OperationKeys;
//import com.sos.fleet.common.constants.OperationTypeKeys;
//import com.sos.fleet.common.constants.StatusKeys;
//import com.sos.fleet.common.domain.SearchPageImpl;
//import com.sos.fleet.common.domain.SearchPageImpl.SimpleOrder;
//import com.sos.fleet.common.domain.VehicleDomain;
//import com.sos.fleet.common.dto.AutoCompleteDto;
//import com.sos.fleet.common.settings.SecuritySettings;
//import com.sos.fleet.common.util.OperationUtil;
//import com.sos.fleet.common.util.SecurityUtil;
//import com.sos.fleet.service.VehicleService;
//
//@RestController
//@Secured(SecuritySettings.ROLE_USER)
//public class VehicleRestController {
//	@Autowired
//	VehicleService vehicleService;
//	
//	@Value("${autocomplete.vehicle.count}")
//	String autoCompleteCount;
//	
//	@RequestMapping("autoComplete")       
//	public Object autoComplete(SearchPageImpl<VehicleDomain> searchPageImpl,VehicleCondition vehicleCondition){
//		Long userId = SecurityUtil.getUserId();
//		if(userId==null){
//			throw new AccessDeniedException("the userId is empty.");
//		}
//		searchPageImpl.setSize(NumberUtils.parseNumber(autoCompleteCount, Integer.class));
//		vehicleCondition.setStatus(StatusKeys.SUCCESS);
//		vehicleCondition.setUserId(userId);
//		searchPageImpl.setCondition(vehicleCondition);
//		vehicleService.findAll(searchPageImpl);
//		List<VehicleDomain> list = 
//				searchPageImpl.getPageResult()!=null&&!CollectionUtils.isEmpty(searchPageImpl.getPageResult().getContent())?
//						searchPageImpl.getPageResult().getContent():null;
//		//!CollectionUtils.isEmpty(list), Security Scaner ignore scan.
//		OperationUtil.log(OperationKeys.AUTO_COMPLETE_FIND_VEHICLE,OperationTypeKeys.MONITOR_LOCATION);
//		if(list!=null&&list.size()>0){
//			List<AutoCompleteDto> autoComplete = new ArrayList<AutoCompleteDto>(list.size());
//			for (int i = 0; i < list.size(); i++) {
//				VehicleDomain vehicle = list.get(i);
//				autoComplete.add(new AutoCompleteDto(vehicle.getVin()+(vehicle.getPlateId()!=null?"("+vehicle.getPlateId()+")":""), vehicle.getVin()));
//			}
//			return autoComplete;
//		}
//		
//		return null;
//	}
//	
//	@RequestMapping({"showVehicleJson"})
//	public Object showVehicle(SearchPageImpl<VehicleDomain> searchPageImpl,VehicleCondition vehicleCondition){
//		Long userId = SecurityUtil.getUserId();
//		if(userId==null){
//			throw new AccessDeniedException("the userId is empty.");
//		}
//		vehicleCondition.setStatus(StatusKeys.SUCCESS);
//		searchPageImpl.setCondition(vehicleCondition);
//		vehicleCondition.setUserId(userId);
//		if(CollectionUtils.isEmpty(searchPageImpl.getOrders())){
//			List<SimpleOrder> orders = new ArrayList<SimpleOrder>(1);
//			orders.add(new SimpleOrder(Direction.DESC,"vehicleBindingDomainsForVin.bindingDate"));
//			searchPageImpl.setOrders(orders );
//		}
//		vehicleService.findAll(searchPageImpl);
//		OperationUtil.log(OperationKeys.SEARCH_VEHICLE,OperationTypeKeys.MONITOR_LOCATION);
//		return searchPageImpl;
//	}
//}
