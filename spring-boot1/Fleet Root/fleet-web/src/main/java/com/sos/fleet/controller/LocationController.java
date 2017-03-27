package com.sos.fleet.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sos.fleet.common.condition.LocationHistoryCondition;
import com.sos.fleet.common.condition.VehicleCondition;
import com.sos.fleet.common.constants.OperationKeys;
import com.sos.fleet.common.constants.OperationTypeKeys;
import com.sos.fleet.common.constants.StatusKeys;
import com.sos.fleet.common.controller.RestClientController;
import com.sos.fleet.common.domain.LocationHistory;
import com.sos.fleet.common.domain.SearchPageImpl;
import com.sos.fleet.common.domain.VehicleDomain;
import com.sos.fleet.common.domain.VehicleDomain_;
import com.sos.fleet.common.domain.SearchPageImpl.SimpleOrder;
import com.sos.fleet.common.settings.SecuritySettings;
import com.sos.fleet.common.util.OperationUtil;
import com.sos.fleet.common.util.SecurityUtil;
import com.sos.fleet.service.LocationHistoryService;
import com.sos.fleet.service.VehicleService;

@Controller
@Secured(SecuritySettings.ROLE_USER)
public class LocationController extends RestClientController {
	Log log = LogFactory.getLog(getClass());
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private LocationHistoryService locationHistoryService;
	
	@RequestMapping({"monitorLocation","/"})
	public ModelAndView monitorLocation(@RequestParam(value="vin",required=false)String vin,VehicleCondition vehicleCondition){
		Long userId = SecurityUtil.getUserId();
		if(userId==null){
			throw new AccessDeniedException("the userId is empty.");
		}
		vehicleCondition.setStatus(StatusKeys.SUCCESS);
		vehicleCondition.setUserId(userId);
		
		Page<VehicleDomain> page = vehicleService.findAll(vehicleCondition, null);
		if(StringUtils.hasText(vin)&&(page==null||!page.hasContent())){
			throw new AccessDeniedException("the vin is denied.");
		}
		ModelAndView mav = new ModelAndView("location/monitorLocation");
		if(StringUtils.hasText(vin)){
			mav.addObject("vin", vin);
		}
		OperationUtil.log(OperationKeys.MONITOR_LOCATION, OperationTypeKeys.MONITOR_LOCATION, vin);
		return mav;
	}
	@RequestMapping("getLocationHistoryByVin")
	public ModelAndView getLocationHistoryByVin(SearchPageImpl<LocationHistory> searchPage,LocationHistoryCondition condition){
		ModelAndView mav = new ModelAndView("location/locationHistory");
		if(!StringUtils.hasText(condition.getVin())){
			throw new AccessDeniedException("has none vin.");
		}
		List<SimpleOrder> orders = new ArrayList<SimpleOrder>(1);
		orders.add(new SimpleOrder(Direction.DESC, "locatedTime"));
		searchPage.setOrders(orders);
		searchPage.setCondition(condition);
		searchPage.setType(SearchPageImpl.TYPE_SIMPLE);
		mav.addObject("page",locationHistoryService.findAllByVinAndCurrentUserId(searchPage));
		List<VehicleDomain> list = vehicleService.findByProperty(VehicleDomain_.vin.getName(), condition.getVin());
		mav.addObject("vehicle",CollectionUtils.isEmpty(list)?null:list.get(0));
		mav.addObject("vin", condition.getVin());
//		OperationUtil.log(OperationKeys.CLICK_LOCATION_HISTORY, OperationTypeKeys.MONITOR_LOCATION, condition.getVin());
		LocationHistory latest = locationHistoryService.getLatestLocationHistoryByVin(condition.getVin());
		String comments = null;
		if(latest!=null){
			comments = new StringBuilder(0).append(latest.getLongitude()).append(',').append(latest.getLatitude()).append(',')
					.append(DateFormatUtils.format(latest.getLocatedTime(), "yyyy-MM-dd HH:mm:ss")).toString();
		}
		OperationUtil.log(null, OperationKeys.CLICK_LOCATION_HISTORY, null, StatusKeys.SUCCESS,  condition.getVin(), OperationTypeKeys.MONITOR_LOCATION, comments);
		return mav;
	}
}
