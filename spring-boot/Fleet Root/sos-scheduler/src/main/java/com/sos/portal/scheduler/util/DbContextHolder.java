/************************************************************************************
 * @File name   :      DbContextHolder.java
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
 * 2015年4月23日 下午1:54:49			junjunzhu			1.0				Initial Version
 ************************************************************************************/
package com.sos.portal.scheduler.util;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 */
public class DbContextHolder {
	protected static final Log log = LogFactory.getLog(DbContextHolder.class);
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	private static  Map<Object, Object> targetDataSources;
	public static String getDbType() {
		String dbType = contextHolder.get();
		if (StringUtils.isBlank(dbType)) {
			if (targetDataSources==null||targetDataSources.size()==0) {
				throw new RuntimeException("Default DB Key is null!");
			}
			dbType = targetDataSources.keySet().iterator().next().toString();
			 log.debug("get default db key: "+dbType);
			return dbType;
		} else {
			 log.debug("get db key: "+dbType);
			return dbType;
		}
	}

	public static void setDbType(String dbType) {
		 log.debug("set  db key: "+dbType);
		contextHolder.set(dbType);
	}

	public static void clear() {
		log.debug("remove db source! ");
		if (contextHolder.get() != null)
			contextHolder.remove();
	}
	
	 static void setTargetDataSources(Map<Object, Object> targetDataSources){
		 log.debug("set targetDataSources[size: "+targetDataSources.size()+"]");
		 DbContextHolder.targetDataSources = targetDataSources;
	}
	 
	 public static  int getDBSize(){
		 return targetDataSources.size();
	 }
	 
	 public static String getDbName(String key){
		 log.debug("get  db name by key["+key+"]");
		 if(targetDataSources.containsKey(key))
			 return key;
		 return null;
	 }
}
