package com.sos.fleet.service;

/************************************************************************************
 * @File name   :      BaseLdapService.java
 *
 * @Author      :      JUNJZHU
 *
 * @Date        :      2012-11-12
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
 * 2012-11-12 上午11:24:03			JUNJZHU			1.0				Initial Version
 ************************************************************************************/

import java.io.Serializable;

public interface BaseLdapService<T, ID extends Serializable>
		{
	/**
	 * @param entity
	 * 			Domain object.
	 * @throws AppException 
	 */
	T save(T entity) ;
	/**
	 * @param entity
	 * 			Domain object.
	 */
	T update(T entity);
	/**
	 * @param entity
	 * 			Domain object.
	 */
	void delete(T entity);
	/**
	 * @param id
	 * 			Primary key of the domain class.
	 * @return Domain object.
	 */
	T get(ID id);
	
	/**
	 * @Author      :      JUNJZHU
	 * @Date        :      2013-1-15
	 * @param password
	 * @param rdn
	 * @return
	 */
	boolean validatePassword(String password, String uid);
	void logicDelete(ID id);
}
