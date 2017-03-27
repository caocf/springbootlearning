/************************************************************************************
 * @File name   :      OvdSchedulerUtil.java
 *
 * @Author      :      junjunzhu
 *
 * @Date        :      2015年4月30日
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
 * 2015年4月30日 下午4:47:22			junjunzhu			1.0				Initial Version
 ************************************************************************************/
package com.sos.portal.scheduler.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.util.CollectionUtils;

import com.sos.portal.scheduler.dto.MailType;

/**
 *
 */
public class SchedulerUtil {
	public static String getFirstCode(String suggest) {
		return suggest.split(",")[0];
	}
	public static String getMailSubject(String subjectFormat, MailType mailType,String taskName) {
		return subjectFormat.replace(
				"{1}",
				!mailType.equals(MailType.ERROR) ? mailType
						.equals(MailType.NOTICE) ? "NOTICE" : "SUCCESS"
						: "ERROR").replace("{0}", taskName);
	}
	public static String getItemRangeJson(List<?> list){
		if(CollectionUtils.isEmpty(list)){
			return null;
		}else{
			Map<String,Object> map = new HashMap<String, Object>(2);
			map.put("first", list.get(0));
			map.put("last", list.get(list.size()-1));
			return JsonUtil.getJson(map);
		}
	}
	
	public static String getExceptionMessage(Exception e){
		String message = ExceptionUtils.getMessage(e);
		if(StringUtils.isNotBlank(message)&&message.length()>3500){
			return message.substring(0,3500);
		}
		return message;
	}
}
