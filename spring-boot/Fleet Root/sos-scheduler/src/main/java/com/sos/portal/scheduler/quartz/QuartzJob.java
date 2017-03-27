/************************************************************************************
 * @File name   :      QuartzJob.java
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
 * 2015年5月20日 下午5:47:28			junjunzhu			1.0				Initial Version
 ************************************************************************************/
package com.sos.portal.scheduler.quartz;


/**
 *
 */
public interface QuartzJob {
	void doTask();
}
