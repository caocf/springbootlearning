/************************************************************************************
 * @File name   :      JasyptUtil.java
 *
 * @Author      :      junjunzhu
 *
 * @Date        :      2015年4月15日
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
 * 2015年4月15日 下午5:22:26			junjunzhu			1.0				Initial Version
 ************************************************************************************/
package com.sos.tpl.common.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 *
 */
public class JasyptUtil {
	public static String encrypt(String source,String key,String algorithm){
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setAlgorithm(algorithm);
		encryptor.setPassword(key);
		return encryptor.encrypt(source);
	}
	
	public static String decrpt(String source,String key,String algorithm){
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setAlgorithm(algorithm);
		encryptor.setPassword(key);
		return encryptor.decrypt(source);
	}
	
	public static String encryptByPBEWithMD5AndDES(String source,String key){
		return encrypt(source, key, "PBEWithMD5AndDES");
	}
	
	public static String decrptByPBEWithMD5AndDES(String source,String key){
		return decrpt(source, key, "PBEWithMD5AndDES");
	}
	public static void main(String[] args) {
		System.out.println(encryptByPBEWithMD5AndDES("Pass2015","dev"));
	}
	public static void main1(String[] args) {
		System.out.println(decrptByPBEWithMD5AndDES("UnuWHb/lCGI6w/hM7Apyn7lyo+qGab5RTQtWjAuuEtw=","dev"));
	}
	
}
