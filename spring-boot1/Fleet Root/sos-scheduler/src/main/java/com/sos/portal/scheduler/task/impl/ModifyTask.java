package com.sos.portal.scheduler.task.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.sos.portal.scheduler.service.ModifyService;
import com.sos.portal.scheduler.task.SpringBootSchedulerTask;
import com.sos.portal.scheduler.util.JsonUtil;
import com.sos.portal.scheduler.work.TaskWork;

@Component
@ConditionalOnProperty(prefix="scheduler.modify",name="enable",havingValue="true")
public class ModifyTask extends SpringBootSchedulerTask<Object> {
	@Autowired
	private ModifyService modifyService;
	@Value("${scheduler.modify.sql:}")
	private String sql;
	@Value("${scheduler.modify.cron:}")
	private String cron;
	@Override
	public List<Object> loadSource(Date fromDate, Date toDate) {
		List<Object> list =  new ArrayList<Object>();
		list.add(new Boolean(true));
		return list;
	}

	@Override
	public void processBatch(List<Object> taskEntity, Date innerTime) {
		modifyService.executeSql(sql);
	}

	@Override
	public String getTaskName() {
		return "modify";
	}

	@Override
	public Date getCreateDate(Object T) {
		return new Date();
	}

	@Override
	public String getItemRangeJson(List<Object> list) {
		return JsonUtil.getJson(new HashMap<Object,Object>());
	}

	public String getSql() {
		return sql;
	}


	@Override
	public String getCronExpression() {
		return cron;
	}

	@Bean(name="modifyTaskWork")
	@Override
	public TaskWork registerTaskWork() {
		return super.registerTaskWork();
	}

}
