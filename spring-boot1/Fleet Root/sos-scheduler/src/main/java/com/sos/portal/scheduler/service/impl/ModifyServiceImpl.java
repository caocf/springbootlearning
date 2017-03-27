/************************************************************************************
 * @File name   :      TestingServiceImpl.java
 *
 * @Author      :      junjunzhu
 *
 * @Date        :      2015年8月10日
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
 * 2015年8月10日 上午11:15:08			junjunzhu			1.0				Initial Version
 ************************************************************************************/
package com.sos.portal.scheduler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sos.portal.scheduler.annotation.DataSource;
import com.sos.portal.scheduler.data.dao.SchedulerLogDao;
import com.sos.portal.scheduler.service.ModifyService;

/**
 *
 */
@Service
@DataSource("fleetDataSource")
public class ModifyServiceImpl implements ModifyService {

	@Autowired
	private SchedulerLogDao schedulerLogDao;
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void executeSql(String... sql){
		if(sql==null)
			return;
		schedulerLogDao.executeSql(sql);
	}

}
