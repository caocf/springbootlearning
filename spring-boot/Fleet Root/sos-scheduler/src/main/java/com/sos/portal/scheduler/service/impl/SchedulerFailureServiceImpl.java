/************************************************************************************
 * @File name   :      SchedulerFailureServiceImpl.java
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
 * 2015年5月5日 下午2:48:27			junjunzhu			1.0				Initial Version
 ************************************************************************************/
package com.sos.portal.scheduler.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.sos.portal.scheduler.annotation.DataSource;
import com.sos.portal.scheduler.data.dao.SchedulerFailDao;
import com.sos.portal.scheduler.domain.SchedulerFailureDomain;
import com.sos.portal.scheduler.service.SchedulerFailureService;

/**
 *
 */
@Service
@DataSource("fleetDataSource")
public class SchedulerFailureServiceImpl extends
		BaseServiceImpl<SchedulerFailureDomain, Long> implements
		SchedulerFailureService {
	private SchedulerFailDao schedulerFailDao;

	/**
	 * @Author : junjunzhu
	 * 
	 * @Date : 2015年5月5日
	 * 
	 * @param schedulerLogDao
	 *            the schedulerLogDao to set
	 */
	@Autowired
	public void setSchedulerFailDao(SchedulerFailDao schedulerFailDao) {
		this.jpaRepository = this.schedulerFailDao = schedulerFailDao;
	}

	/**
	 * overridden:
	 * 
	 * @Author : junjunzhu
	 * @Date : 2015年5月5日
	 * @see com.sos.portal.scheduler.service.SchedulerFailureService#getRerunList(java.lang.String)
	 **/
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<SchedulerFailureDomain> getRerunList(final String taskName) {
		// "from SchedulerFailureDomain where name=? and activeAutoRerun = true and rerunStatus<>1 order by id"
		// findByNameAndActiveAutoRerunTrueAndRerunStatusNot
		return schedulerFailDao.findAll(
				new Specification<SchedulerFailureDomain>() {
					@Override
					public Predicate toPredicate(
							Root<SchedulerFailureDomain> root,
							CriteriaQuery<?> query, CriteriaBuilder cb) {
						cb.and(cb.equal(root.get("name"), taskName),
								cb.notEqual(root.get("rerunStatus"), 1),
								cb.equal(root.get("activeAutoRerun"), true));
						return null;
					}
				}, new Sort("id"));
	}

	/**
	 * overridden:
	 * 
	 * @Author : junjunzhu
	 * @Date : 2015年5月5日
	 * @see com.sos.portal.scheduler.service.SchedulerFailureService#getRerunList(java.lang.String,
	 *      java.util.List)
	 **/
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
	public List<SchedulerFailureDomain> getRerunList(final String taskName,
			final List<Serializable> ids) {
		return schedulerFailDao.findAll(
				new Specification<SchedulerFailureDomain>() {
					@Override
					public Predicate toPredicate(
							Root<SchedulerFailureDomain> root,
							CriteriaQuery<?> query, CriteriaBuilder cb) {
						ArrayList<Predicate> list = new ArrayList<Predicate>(3);
						list.add(cb.equal(root.get("name"), taskName));
						list.add(cb.notEqual(root.get("rerunStatus"), 1));
						list.add(cb.equal(root.get("activeAutoRerun"), true));
						if (!CollectionUtils.isEmpty(ids)) {
							list.add(root.in(ids));
						}
						list.trimToSize();
						return cb.and(list.toArray(new Predicate[list.size()]));
					}
				}, new Sort("id"));
	}

}
