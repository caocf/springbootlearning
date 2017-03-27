package com.sos.portal.scheduler.service;

import java.util.Date;
import java.util.List;

import com.sos.portal.scheduler.domain.LocationHistory;

public interface LocationHistoryService extends
		BaseService<LocationHistory, Long> {
	
	void batchProcess(List<LocationHistory> list,Date innerDate);
}
