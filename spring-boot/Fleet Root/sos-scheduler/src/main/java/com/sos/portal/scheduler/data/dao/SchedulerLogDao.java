/************************************************************************************
 * @File name   :      SchedulerLogDao.java
 *
 * @Author      :      junjunzhu
 *
 * @Date        :      2015年4月29日
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
 * 2015年4月29日 下午4:11:38			junjunzhu			1.0				Initial Version
 ************************************************************************************/
package com.sos.portal.scheduler.data.dao;

import org.springframework.stereotype.Repository;

import com.sos.portal.scheduler.data.BaseRepository;
import com.sos.portal.scheduler.domain.SchedulerLogDomain;

/**
 *
 */
@Repository
public interface SchedulerLogDao  extends BaseRepository<SchedulerLogDomain, Long> {
}
