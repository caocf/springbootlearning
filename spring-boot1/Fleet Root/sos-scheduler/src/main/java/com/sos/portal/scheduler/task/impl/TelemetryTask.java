package com.sos.portal.scheduler.task.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.sos.portal.scheduler.domain.LocationHistory;
import com.sos.portal.scheduler.service.LocationHistoryService;
import com.sos.portal.scheduler.service.TelemetryService;
import com.sos.portal.scheduler.task.SpringBootSchedulerTask;
import com.sos.portal.scheduler.work.TaskWork;
@Component
@ConditionalOnProperty(prefix="scheduler.telemetry",name="enable",havingValue="true")
public class TelemetryTask extends SpringBootSchedulerTask<LocationHistory> {
	@Autowired
	private TelemetryService telemetryService;
	
	@Autowired
	private LocationHistoryService locationHistoryService;
	@Value("${scheduler.telemetry.cron}")
	String cron;
	@Override
	public List<LocationHistory> loadSource(Date fromDate, Date toDate) {
		return telemetryService.findInDaysRange(fromDate, toDate);
	}

	@Override
	public void processBatch(List<LocationHistory> taskEntity, Date innerTime) {
		locationHistoryService.batchProcess(taskEntity, innerTime);
	}

	@Override
	public String getTaskName() {
		return "telemetry";
	}

	@Override
	public Date getCreateDate(LocationHistory T) {
		return T.getLocatedTime();
	}

	@Override
	public String getCronExpression() {
		return cron;
	}

	@Override
	@Bean(name="telemetryTaskWork")
	public TaskWork registerTaskWork() {
		return super.registerTaskWork();
	}

}
