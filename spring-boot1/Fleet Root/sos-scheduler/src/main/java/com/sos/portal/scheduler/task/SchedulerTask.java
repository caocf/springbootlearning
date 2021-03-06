/************************************************************************************
 * @File name   :      SchedulerTask.java
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
 * 2015年5月20日 上午11:01:27			junjunzhu			1.0				Initial Version
 ************************************************************************************/
package com.sos.portal.scheduler.task;

import java.util.Date;
import java.util.List;

import com.sos.portal.scheduler.work.TaskWork;


/**
 *
 */
public interface SchedulerTask<T> {
	List<T> loadSource(Date fromDate,Date toDate) ;
	void processBatch(List<T> taskEntity,Date innerTime);
	String getTaskName();
	Date getCreateDate(T T);
	String getItemRangeJson(List<T> list);
	String getCronExpression();
	TaskWork registerTaskWork();
	
}
