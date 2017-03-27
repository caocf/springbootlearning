package com.sos.fleet.service;

import com.sos.fleet.common.domain.LocationHistory;
import com.sos.fleet.common.domain.SearchPageImpl;

public interface LocationHistoryService extends
		BaseService<LocationHistory, Long> {
	SearchPageImpl<LocationHistory> findAllByVinAndCurrentUserId(SearchPageImpl<LocationHistory> searchPage);
	LocationHistory getLatestLocationHistoryByVin(String vin);
}
