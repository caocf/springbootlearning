/************************************************************************************
 * @File name   :      SchedulerLogService.java
 *
 * @Author      :      junjunzhu
 *
 * @Date        :      2015年5月5日
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
 * 2015年5月5日 下午2:47:56			junjunzhu			1.0				Initial Version
 ************************************************************************************/
package com.sos.portal.scheduler.service;

import com.sos.portal.scheduler.domain.SchedulerLogDomain;

/**
 *
 */
public interface SchedulerLogService extends BaseService<SchedulerLogDomain, Long>{
	 SchedulerLogDomain getLastSchedulerByName(String name);
	 void updateStatusByIds(Long[] ids,int status);
}
