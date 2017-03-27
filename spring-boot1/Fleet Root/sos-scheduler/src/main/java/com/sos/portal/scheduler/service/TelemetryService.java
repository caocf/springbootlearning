package com.sos.portal.scheduler.service;

import java.util.Date;
import java.util.List;

import com.sos.portal.scheduler.domain.LocationHistory;


@SuppressWarnings("rawtypes")
public interface TelemetryService extends BaseService{
	public List<LocationHistory> findInDaysRange(Date start,Date end);
}
