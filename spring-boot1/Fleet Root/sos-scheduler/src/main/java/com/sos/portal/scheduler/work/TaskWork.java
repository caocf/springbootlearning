/************************************************************************************
 * @File name   :      TaskWork.java
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
 * 2015年5月20日 上午11:18:20			junjunzhu			1.0				Initial Version
 ************************************************************************************/
package com.sos.portal.scheduler.work;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.sos.portal.scheduler.domain.SchedulerLogDomain;

/**
 *
 */
public interface TaskWork {
	SchedulerLogDomain automateTask() ;

	SchedulerLogDomain manualTask(Date fromDate, Date toDate) ;

	void automateRerun() ;

	void manualRerun(List<Serializable> id) ;

	Date[] getAutomateSearchDateRange();

	String getTaskName();
}
