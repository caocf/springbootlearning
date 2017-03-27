/************************************************************************************
 * @File name   :      SchedulerTaskWork.java
 *
 * @Author      :      junjunzhu
 *
 * @Date        :      2015年5月20日
 *
 * @Copyright Notice: 
 * Copyright (c) 2012 Shanghai OnStar, Inc. All  Rights Reserved.
 * This software is published under the terms of the Shanghai OnStar Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date								Who					Version				Comments
 * 2015年5月20日 下午1:06:57			junjunzhu			1.0				Initial Version
 ************************************************************************************/
package com.sos.portal.scheduler.work.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.sos.portal.scheduler.domain.SchedulerFailureDomain;
import com.sos.portal.scheduler.domain.SchedulerLogDomain;
import com.sos.portal.scheduler.exception.SchedulerFailureException;
import com.sos.portal.scheduler.service.SchedulerFailureService;
import com.sos.portal.scheduler.service.SchedulerLogService;
import com.sos.portal.scheduler.settings.SchedulerSettings;
import com.sos.portal.scheduler.task.SchedulerTask;
import com.sos.portal.scheduler.util.SchedulerUtil;
import com.sos.portal.scheduler.work.TaskWork;

/**
 *
 */
public class SchedulerTaskWork implements TaskWork{
	public static final int STATUS_SUCCESS=1;
	public static final int STATUS_INITAL=0;
	public static final int STATUS_FAILURE=2;
	public static final String APP_NAME="FLEET";
	
	Log log = LogFactory.getLog(getClass());
/*	@Value("${scheduler.transaction.batch.count}")
	private String count;
	@Value("${scheduler.rerun.valid.times}")
	private String rerunTimes;*/
	@Autowired
	private SchedulerLogService schedulerLogService;
	@Autowired
	private SchedulerFailureService schedulerFailureService;
	/*@Value("${scheduler.test.date.enable}")
	private String isTest;
	@Value("${scheduler.test.from.date}")
	private String testFromDate;
	@Value("${scheduler.test.to.date}")
	private String testToDate;*/
	
	@Autowired
	private SchedulerSettings schedulerSettings;
	
	private SchedulerTask<Object> schedulerTask;
	
	/** 
	 * overridden:
	 * @throws AppException 
	 * @Author      :      junjunzhu
	 * @Date        :      2015年5月20日
	 * @see com.sos.portal.scheduler.work.TaskWork#automateTask()
	**/
	@Override
	public SchedulerLogDomain automateTask() {
		Date[] dateRange = getAutomateSearchDateRange();
		return run(dateRange[0], dateRange[1], false);
	}
	/** 
	 * overridden:
	 * @throws AppException 
	 * @Author      :      junjunzhu
	 * @Date        :      2015年5月20日
	 * @see com.sos.portal.scheduler.work.TaskWork#manualTask(java.util.Date, java.util.Date)
	**/
	@Override
	public SchedulerLogDomain manualTask(Date fromDate, Date toDate){
			return run(fromDate, toDate, true);
	}

	/** 
	 * overridden:
	 * @throws AppException 
	 * @Author      :      junjunzhu
	 * @Date        :      2015年5月20日
	 * @see com.sos.portal.scheduler.work.TaskWork#automateRerun()
	**/
	@Override
	public void automateRerun(){
			rerun(null);
	}

	/** 
	 * overridden:
	 * @throws AppException 
	 * @Author      :      junjunzhu
	 * @Date        :      2015年5月20日
	 * @see com.sos.portal.scheduler.work.TaskWork#manualRerun(java.util.List)
	**/
	@Override
	public void manualRerun(List<Serializable> id) {
			rerun(id);
	}
	public SchedulerLogDomain run(Date firstItemCreateDate, Date lastItemCreateDate,
			boolean isSpecifyDate)  {
		List<Object> list = null;
		Date runStartDate = new Date();
		list = schedulerTask.loadSource(firstItemCreateDate,
				lastItemCreateDate);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		SchedulerLogDomain scheduler = new SchedulerLogDomain(null, getTaskName(),
				runStartDate, null, STATUS_INITAL, APP_NAME,
				schedulerTask.getCreateDate(list.get(0)),schedulerTask.getCreateDate( list.get(list.size() - 1)), schedulerTask.getItemRangeJson(list),
				list.size(), 0, isSpecifyDate, null, null);
		schedulerLogService.save(scheduler);
		log.debug("source list size : " + list.size());
		int splitCount =schedulerSettings.getBatchCount();
		log.debug("splitCount : " + splitCount);
		int length = list.size() % splitCount > 0 ? list.size() / splitCount
				+ 1 : list.size() / splitCount;
		log.debug("Loop Length : " + length);
		List<SchedulerFailureDomain> failList = new LinkedList<SchedulerFailureDomain>();
		int failureCount=0;
		for (int i = 0; i < length; i++) {
			int endIndex = (i + 1) * splitCount;
			int toSize = endIndex > list.size() ? list.size() : endIndex;
			List<Object> tempList = list.subList(i * splitCount, toSize);
			Date innerRunTime = new Date();
			try {
				/*if(i%3==0){
					throw new AppException("500", "test exception");
				}*/
				schedulerTask.processBatch(tempList, innerRunTime);
			} catch (RuntimeException e) {
				log.error(ExceptionUtils.getMessage(e));
				int rerunTimesInt =schedulerSettings.getRerunTimes();
				SchedulerFailureDomain failure = new SchedulerFailureDomain(
						null, innerRunTime, new Date(), scheduler.getId(),
						getTaskName(), APP_NAME, schedulerTask.getCreateDate(tempList.get(0)), 
						schedulerTask.getCreateDate(tempList.get(
								tempList.size() - 1)),
						schedulerTask.getItemRangeJson(tempList),
						tempList.size(), STATUS_INITAL, null, null, 0,
						SchedulerUtil.getExceptionMessage(e), rerunTimesInt>0,
						false, null, null,i * splitCount,toSize);
				failList.add(failure);
				failureCount+=tempList.size();
				schedulerFailureService.save(failure);
			}
		}
		scheduler.setExecuteEndDate(new Date());
		if(failList.size()>0){
			scheduler.setSourceFailure(failureCount);
			scheduler.setStatus(STATUS_FAILURE);
			schedulerLogService.update(scheduler);
			throw new SchedulerFailureException(null, failList);
		}else{
			log.debug("update scheduler log: " +STATUS_SUCCESS);
			scheduler.setStatus(STATUS_SUCCESS);
			schedulerLogService.update(scheduler);
			log.debug("end update scheduler log: " +STATUS_SUCCESS);
		}
		return scheduler;
	}
	
	public void rerun(List<Serializable> ids){
		List<SchedulerFailureDomain> list =CollectionUtils.isEmpty(ids)?schedulerFailureService.getRerunList(getTaskName()):
			schedulerFailureService.getRerunList(getTaskName(), ids);
		if(CollectionUtils.isEmpty(list)){
			log.info("re-run load history empty : "+ getTaskName());
			return ;
		}
		log.info("Reruning task : "+ getTaskName());
		List<SchedulerFailureDomain> failList = new LinkedList<SchedulerFailureDomain>();
		int rerunTimesInt =schedulerSettings.getRerunTimes();
		for (int i = 0; i < list.size(); i++) {
			SchedulerFailureDomain domain = list.get(i);
			if(domain.getRerunCount()==null){
				domain.setRerunCount(0);
			}
			if(domain.getRerunCount()>=rerunTimesInt){
				continue;
			}
			Date runStartDate = new Date();
			domain.setRerunCount(domain.getRerunCount()+1);
			domain.setLastRerunStartDate(runStartDate);
			domain.setIsManualRerun(!CollectionUtils.isEmpty(ids));
			List<Object> mtList = null;
			log.info("get search source date range: " + DateFormatUtils.ISO_DATETIME_FORMAT.format(domain.getSourceFirstDate())+" - " +
					DateFormatUtils.ISO_DATETIME_FORMAT.format(domain.getSourceLastDate()));
			mtList =schedulerTask.loadSource(domain.getSourceFirstDate(), domain.getSourceLastDate());
			if (CollectionUtils.isEmpty(list)) {
				log.info("Re-run load OVD Empty: "+ getTaskName());
				return ;
			}
			try {
				/*
				 * 测试rerun代码
				 * if(i%3==0||i%5==0){
					throw new AppException("500", "test exception");
				}*/
				schedulerTask.processBatch(mtList, runStartDate);
				domain.setRerunStatus(STATUS_SUCCESS);
			}catch(RuntimeException e){
				log.error(ExceptionUtils.getMessage(e));
				domain.setRerunStatus(STATUS_FAILURE);
				domain.setLastFailureComments(SchedulerUtil.getExceptionMessage(e));
				if(domain.getRerunCount()>=rerunTimesInt){
					domain.setActiveAutoRerun(false);
				}
				failList.add(domain);
			}
			domain.setLastRerunEndDate(new Date());
			schedulerFailureService.update(domain);
		}
		if(failList.size()>0){
			throw new SchedulerFailureException(null, failList);
		}
	}
	/** 
	 * overridden:
	 * @Author      :      junjunzhu
	 * @Date        :      2015年5月20日
	 * @see com.sos.portal.scheduler.work.TaskWork#getAutomateSearchDate()
	**/
	@Override
	public Date[] getAutomateSearchDateRange() {
		SchedulerLogDomain scheLog = null;
		Date toDate = new Date();
		Date fromDate = null;
		if(schedulerSettings.isTestDateEnable()){
				try {
					fromDate = DateUtils.parseDate(schedulerSettings.getTestFromDate(), "yyyy-MM-dd HH:mm:ss");
					toDate =DateUtils.parseDate(schedulerSettings.getTestToDate(), "yyyy-MM-dd HH:mm:ss");
				} catch (ParseException e) {
						log.error(ExceptionUtils.getMessage(e));
						return null;
				}
				
		}else if ((scheLog=schedulerLogService
				.getLastSchedulerByName(getTaskName())) == null || scheLog.getSourceToDate() == null) {
			fromDate = DateUtils.addDays(toDate, -1);
		} else {
			fromDate = scheLog.getSourceToDate();
		} 
		log.info("search source date from "+DateFormatUtils.ISO_DATETIME_FORMAT.format(fromDate)+" to "+
				DateFormatUtils.ISO_DATETIME_FORMAT.format(toDate)+": "+schedulerTask.getTaskName());
		return new Date[]{fromDate,toDate};
	}

	/**
	 * @Author      :      junjunzhu
	 *
	 * @Date        :      2015年5月20日
	 *
	 * @param schedulerTask the schedulerTask to set
	 */
	public void setSchedulerTask(SchedulerTask<Object> schedulerTask) {
		this.schedulerTask = schedulerTask;
	}
	@Override
	public String getTaskName() {
		return schedulerTask.getTaskName();
	}
	
	
}
