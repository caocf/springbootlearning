/************************************************************************************
 * @File name   :      DynamicCodeUtil.java
 *
 * @Author      :      LOZHANG
 *
 * @Date        :      Dec 27, 2012
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
 * Dec 27, 2012 8:58:00 PM			LOZHANG			1.0				Initial Version
 ************************************************************************************/
package com.sos.fleet.common.util;

import java.util.Random;

/**
 *
 */
public class DynamicCodeUtil {
	public static String getDynamicCode() {
//		String code = String.valueOf(Math.round(Math.random()*1000000));
//		if(code.length()<6) {
//			StringBuffer sb = new StringBuffer();
//			for(int i=0;i<6-code.length();i++) {
//				sb.append("0");
//			}
//			code=sb+code;
//		}
		Random rand = new Random();
		rand.setSeed(System.currentTimeMillis());
		return String.valueOf(rand.nextInt(899999)+100001);
	}
}
