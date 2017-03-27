/************************************************************************************
 * @File name   :      DynamicDataSource.java
 *
 * @Author      :      junjunzhu
 *
 * @Date        :      2015年4月23日
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
 * 2015年4月23日 下午1:54:18			junjunzhu			1.0				Initial Version
 ************************************************************************************/
package com.sos.portal.scheduler.util;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
	protected static Log log = LogFactory.getLog(AbstractRoutingDataSource.class);
	/**
	 * overridden:
	 * 
	 * @Author : junjunzhu
	 * @Date : 2015年4月23日
	 * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#setTargetDataSources(java.util.Map)
	 **/
	@Override
	public void setTargetDataSources(Map<Object, Object> targetDataSources) {
		super.setTargetDataSources(targetDataSources);
		DbContextHolder.setTargetDataSources(targetDataSources);
	}

	/**
	 * overridden:
	 * 
	 * @Author : junjunzhu
	 * @Date : 2015年4月23日
	 * @see org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource#determineCurrentLookupKey()
	 **/
	@Override
	protected Object determineCurrentLookupKey() {
		String dbType = DbContextHolder.getDbType();
		log.info("Get db type is : "+dbType);
		return dbType;
	}

}
