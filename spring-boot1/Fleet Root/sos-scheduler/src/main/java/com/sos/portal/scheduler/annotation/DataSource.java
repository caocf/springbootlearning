/************************************************************************************
 * @File name   :      DataSource.java
 *
 * @Author      :      junjunzhu
 *
 * @Date        :      2015年5月11日
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
 * 2015年5月11日 下午1:22:51			junjunzhu			1.0				Initial Version
 ************************************************************************************/
package com.sos.portal.scheduler.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
	String value();
}
