package com.sos.fleet.controller.rest;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sos.fleet.common.condition.LocationHistoryCondition;
import com.sos.fleet.common.condition.VehicleCondition;
import com.sos.fleet.common.constants.MockKeys;
import com.sos.fleet.common.constants.OperationKeys;
import com.sos.fleet.common.constants.OperationTypeKeys;
import com.sos.fleet.common.constants.StatusKeys;
import com.sos.fleet.common.controller.RestClientController;
import com.sos.fleet.common.domain.LocationHistory;
import com.sos.fleet.common.domain.SearchPageImpl;
import com.sos.fleet.common.domain.SearchPageImpl.SimpleOrder;
import com.sos.fleet.common.domain.VehicleDomain;
import com.sos.fleet.common.dto.LocationDto;
import com.sos.fleet.common.settings.SecuritySettings;
import com.sos.fleet.common.settings.WebServiceURLSettings;
import com.sos.fleet.common.util.OperationUtil;
import com.sos.fleet.common.util.SecurityUtil;
import com.sos.fleet.common.util.UrlPathUtil;
import com.sos.fleet.common.util.bean.ResultHolder;
import com.sos.fleet.service.LocationHistoryService;
import com.sos.fleet.service.VehicleService;

@RestController
@Secured(SecuritySettings.ROLE_USER)
public class LocationRestController extends RestClientController {
	Log log = LogFactory.getLog(getClass());
	@Value("${mock.ws.enable:}")
	private String enableMock;
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private LocationHistoryService locationHistoryService;

	@RequestMapping("getLocationByVin")
	public Object getLocationByVin(@RequestParam("vin") String vin,
			HttpServletRequest request, VehicleCondition vehicleCondition) {
		Long userId = SecurityUtil.getUserId();
		if (userId == null) {
			throw new AccessDeniedException("the userId is empty.");
		}
		vehicleCondition.setUserId(SecurityUtil.getUserId());
		vehicleCondition.setStatus(StatusKeys.SUCCESS);
		Page<VehicleDomain> page = vehicleService.findAll(vehicleCondition,
				null);
		if (page == null || !page.hasContent()) {
			throw new AccessDeniedException("the vin is denied.");
		}
		VehicleDomain vd = page.getContent().get(0);
		boolean isMock = BooleanUtils.toBoolean(enableMock);
		String url;
		ResultHolder<List<LocationDto>> rs = null;
		String mes = null;
		String comments=null;
		try {
			if (isMock) {
				url = new StringBuilder(request.getScheme()).append("://")
						.append(request.getServerName()).append(":")
						.append(request.getServerPort())
						.append(UrlPathUtil.getContextPath(request))
						.append(MockKeys.GET_LOCATION_BY_VIN).toString();
			
			} else {
				url = WebServiceURLSettings.getWSUrl(WebServiceURLSettings
						.instance().getGetLocationByVin());
			}
			rs = restClient
					.getForObject(
							url,
							new ParameterizedTypeReference<ResultHolder<List<LocationDto>>>() {
							}, vin);
			if (rs != null && !CollectionUtils.isEmpty(rs.getData())
					&& rs.getData().get(0) != null) {
				rs.getData().get(0).setVehicleDomain(vd);
			}

			if (rs == null) {
				mes = "result set is null";
			}
			if (rs != null && !rs.getSuccess()) {
				mes = rs.getCode();
			}else if(rs != null && !CollectionUtils.isEmpty(rs.getData())&& rs.getData().get(0) != null){
				StringBuilder  commentsBuild = new StringBuilder(0).append(rs.getData().get(0).getLon())
						.append(',')
						.append(rs.getData().get(0).getLat())
						.append(',').append(rs.getData().get(0).getLocatedTimeText());
				comments = commentsBuild.toString();
				
			}
		} catch (Exception e) {
			log.error(e, e);
			mes = e.getMessage();
		}
		OperationUtil.log((Long) null, OperationKeys.GET_LOCATION_BY_VIN, null,
				mes == null ? StatusKeys.SUCCESS : StatusKeys.FAILURE, vin,
				OperationTypeKeys.MONITOR_LOCATION, comments==null?mes:comments);
		// OperationUtil.log(OperationKeys.GET_LOCATION_BY_VIN,OperationTypeKeys.MONITOR_LOCATION,vin);
		return rs;
	}

	@RequestMapping("getRestLocationHistoryByVin")
	public Object getLocationHistoryByVin(
			SearchPageImpl<LocationHistory> searchPage,
			LocationHistoryCondition condition) {
		if (!StringUtils.hasText(condition.getVin())) {
			throw new AccessDeniedException("has none vin.");
		}
		searchPage.setCondition(condition);
		searchPage.setType(SearchPageImpl.TYPE_SIMPLE);
		List<SimpleOrder> orders = new ArrayList<SimpleOrder>(1);
		orders.add(new SimpleOrder(Direction.DESC, "locatedTime"));
		searchPage.setOrders(orders);
//		OperationUtil.log(OperationKeys.GET_LOCATION_HISTORY,
//				OperationTypeKeys.MONITOR_LOCATION, condition.getVin());
		LocationHistory latest = locationHistoryService.getLatestLocationHistoryByVin(condition.getVin());
		String comments = null;
		if(latest!=null){
			comments = new StringBuilder(0).append(latest.getLongitude()).append(',').append(latest.getLatitude()).append(',')
					.append(DateFormatUtils.format(latest.getLocatedTime(), "yyyy-MM-dd HH:mm:ss")).toString();
		}
		OperationUtil.log(null, OperationKeys.GET_LOCATION_HISTORY, null, StatusKeys.SUCCESS,  condition.getVin(), OperationTypeKeys.MONITOR_LOCATION, comments);
		return locationHistoryService.findAllByVinAndCurrentUserId(searchPage);
	}
}
