/************************************************************************************
 * @File name   :      RegexUtil.java
 *
 * @Author      :      JUNJZHU
 *
 * @Date        :      2012-11-15
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
 * 2012-11-15 上午9:13:27			JUNJZHU			1.0				Initial Version
 ************************************************************************************/

package com.sos.fleet.common.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

	// 数字
	public static final String REG_DECIMAL = "-?\\d+(\\.\\d)?\\d*";
	// 正数
	public static final String REG_POSITIVE_DECIMAL = "\\d+(\\.\\d)?\\d*";
	// 负数
	public static final String REG_NEGATIVE_DECIMAL = "-\\d+(\\.\\d)?\\d*";
	// 整数
	public static final String REG_INTEGER = "(-?[1-9][0-9]*)|0+";
	// 正整数和0
	public static final String REG_POSITIVE_INTEGER = "\\d+";
	// 正整
	public static final String REG_POSITIVE_INTEGER_NON_0 = "[1-9]\\d*";
	// 负整
	public static final String REG_NEGATIVE_INTEGER = "-[1-9]\\d*";
	// 正数
	public static final String REG_POSITIVE_NUM = "\\d+(\\.\\d)?\\d*";
	// 负数
	public static final String REG_NEGATIVE_NUM = "-\\d+(\\.\\d)?\\d*";

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param reg
	 * @return pattern
	 */
	public static Pattern getPattern(String reg) {
		return Pattern.compile(reg);
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param regname
	 * @param value
	 * @return matcher
	 */
	public static Matcher getMatcher(String regname, String value) {
		return getPattern(regname).matcher(value);
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param regname
	 * @param value
	 * @return matcher
	 */
	public static boolean test(String regname, String value) {
		Matcher ma = getMatcher(regname, value);
		return ma.matches();
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param value
	 * @return
	 */
	public static boolean isDecimal(String value) {
		return test(REG_DECIMAL, value);
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param value
	 * @return null
	 */
	public static Double getDecimal(String value) {
		if (isDecimal(value)) {
			return Double.parseDouble(value);
		}
		return null;
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param value
	 * @return null
	 */
	public static Float getFloat(String value) {
		if (isDecimal(value)) {
			return Float.parseFloat(value);
		}
		return null;
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param value
	 * @return positiveDecimal
	 */
	public static boolean isPositiveDecimal(String value) {
		return test(REG_POSITIVE_DECIMAL, value);
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param value
	 * @return null
	 */
	public static Double getPositiveDecimal(String value) {
		if (isPositiveDecimal(value)) {
			return Double.parseDouble(value);
		}
		return null;
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param value
	 * @return negativeDecimal
	 */
	public static boolean isNegativeDecimal(String value) {
		return test(REG_NEGATIVE_DECIMAL, value);
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param value
	 * @return null
	 */
	public static Double getNegativeDecimal(String value) {
		if (isNegativeDecimal(value)) {
			return Double.parseDouble(value);
		}
		return null;
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param value
	 * @return integer
	 */
	public static boolean isInteger(String value) {
		return test(REG_INTEGER, value);
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param value
	 * @return null
	 */
	public static Integer getInteger(String value) {
		if (isInteger(value)) {
			return Integer.parseInt(value);
		}
		return null;
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param value
	 * @return null
	 */
	public static Long getLong(String value) {
		if (isInteger(value)) {
			return Long.parseLong(value);
		}
		return null;
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param value
	 * @return positiveInteger
	 */
	public static boolean isPositiveInteger(String value) {
		return test(REG_POSITIVE_INTEGER, value);
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param value
	 * @return null
	 */
	public static Integer getPositiveInteger(String value) {
		if (isPositiveInteger(value)) {
			return Integer.parseInt(value);
		}
		return null;
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param value
	 * @return positiveIntegerNon0
	 */
	public static boolean isPositiveIntegerNon0(String value) {
		return test(REG_POSITIVE_INTEGER_NON_0, value);
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param value
	 * @return null
	 */
	public static Integer getPositiveIntegerNon0(String value) {
		if (isPositiveIntegerNon0(value)) {
			return Integer.parseInt(value);
		}
		return null;
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param value
	 * @return negativeInteger
	 */
	public static boolean isNegativeInteger(String value) {
		return test(REG_NEGATIVE_INTEGER, value);
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param value
	 * @return null
	 */
	public static Integer getNegativeInteger(String value) {
		if (isNegativeInteger(value)) {
			return Integer.parseInt(value);
		}
		return null;
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param value
	 * @return positiveNum
	 */
	public static boolean isPositiveNum(String value) {
		return test(REG_POSITIVE_NUM, value);
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param value
	 * @return null
	 */
	public static Double getPositiveNum(String value) {
		if (isPositiveNum(value)) {
			return Double.parseDouble(value);
		}
		return null;
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param value
	 * @return negativeNum
	 */
	public static boolean isNegativeNum(String value) {
		return test(REG_NEGATIVE_NUM, value);
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param value
	 * @return null
	 */
	public static Double getNegativeNum(String value) {
		if (isNegativeNum(value)) {
			return Double.parseDouble(value);
		}
		return null;
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param regex
	 * @param value
	 * @return null
	 */
	public static String value(String regex, String value) {
		Matcher matcher = getMatcher(regex, value);
		if (matcher.find()) {
			return matcher.group();
		}
		return null;
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param regex
	 * @param value
	 * @return null
	 */
	public static String[] values(String regex, String value) {
		Matcher matcher = getMatcher(regex, value);
		ArrayList<String> list = null;
		while (matcher.find()) {
			list = list == null ? new ArrayList<String>(0) : list;
			list.add(matcher.group());
		}
		if (list != null) {
			list.trimToSize();
			return list.toArray(new String[list.size()]);
		}
		return null;
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param regex
	 * @param value
	 * @return false
	 */
	public static boolean has(String regex, String value) {
		Matcher matcher = getMatcher(regex, value);
		if (matcher.find())
			return true;
		return false;
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param regex
	 * @param value
	 * @return count
	 */
	public static int count(String regex, String value) {
		Matcher matcher = getMatcher(regex, value);
		int count = 0;
		while (matcher.find()) {
			count++;
		}
		return count;
	}

	/**
	 * @Author : XIAOXCHE
	 * @Date : 2012-12-10
	 * @param regex
	 * @param value
	 * @return -1
	 */
	public static int indexOf(String regex, String value) {
		Matcher matcher = getMatcher(regex, value);
		if (matcher.find())
			return matcher.start();
		return -1;
	}
}
