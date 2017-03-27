/************************************************************************************
 * @File name   :      SchedulerFailureException.java
 *
 * @Author      :      junjunzhu
 *
 * @Date        :      2015年5月5日
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
 * 2015年5月5日 下午3:20:02			junjunzhu			1.0				Initial Version
 ************************************************************************************/
package com.sos.portal.scheduler.exception;

import java.util.List;

import com.sos.portal.scheduler.domain.SchedulerFailureDomain;

/**
 *
 */
public class SchedulerFailureException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6878083719258537432L;
	private List<SchedulerFailureDomain> domains;


	/**
	 * @Author      :      junjunzhu
	 * @Date        :      2015年5月5日
	 * @param errCode
	 * @param description
	 * @param domain
	 */
	public SchedulerFailureException(	Throwable cause,
			List<SchedulerFailureDomain> domains) {
		this.domains = domains;
	}


	/** 
	 * overridden:
	 * @Author      :      junjunzhu
	 * @Date        :      2015年5月5日
	 * @see java.lang.Throwable#getMessage()
	**/
	@Override
	public String getMessage() {
		return this.getCause()==null?"Scheduler Failure.":this.getMessage();
	}

	/** 
	 * overridden:
	 * @Author      :      junjunzhu
	 * @Date        :      2015年5月5日
	 * @see java.lang.Throwable#getLocalizedMessage()
	**/
	@Override
	public String getLocalizedMessage() {
		return this.getCause()==null?"Scheduler Failure.":this.getLocalizedMessage();
	}


	/**
	 * @Author      :      junjunzhu
	 *
	 * @Date        :      2015年5月5日
	 *
	 * @return the domains
	 */
	public List<SchedulerFailureDomain> getDomains() {
		return domains;
	}


	/**
	 * @Author      :      junjunzhu
	 *
	 * @Date        :      2015年5月5日
	 *
	 * @param domains the domains to set
	 */
	public void setDomains(List<SchedulerFailureDomain> domains) {
		this.domains = domains;
	}
}
