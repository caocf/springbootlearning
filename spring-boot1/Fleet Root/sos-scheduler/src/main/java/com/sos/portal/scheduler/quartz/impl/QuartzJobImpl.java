/************************************************************************************
 * @File name   :      QuartzJobImpl.java
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
 * 2015年5月20日 下午5:50:02			junjunzhu			1.0				Initial Version
 ************************************************************************************/
package com.sos.portal.scheduler.quartz.impl;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sos.portal.scheduler.quartz.QuartzJob;
import com.sos.portal.scheduler.work.TaskWork;

/**
 *
 */
public class QuartzJobImpl implements QuartzJob{
	Log log = LogFactory.getLog(getClass());
	private TaskWork taskWork;
	/** 
	 * overridden:
	 * @Author      :      junjunzhu
	 * @Date        :      2015年5月20日
	 * @see com.sos.portal.scheduler.quartz.QuartzJob#doTask()
	**/
	@Override
	public void doTask() {
		try {
			taskWork.automateRerun();
		} catch (RuntimeException e) {
			log.debug(ExceptionUtils.getMessage(e));
		}
		try {
			taskWork.automateTask();
		} catch (RuntimeException e) {
			log.debug(ExceptionUtils.getMessage(e));
		}
	}
	/**
	 * @Author      :      junjunzhu
	 *
	 * @Date        :      2015年5月20日
	 *
	 * @param taskWor the taskWor to set
	 */
	public void setTaskWork(TaskWork taskWork) {
		this.taskWork = taskWork;
	}

}
