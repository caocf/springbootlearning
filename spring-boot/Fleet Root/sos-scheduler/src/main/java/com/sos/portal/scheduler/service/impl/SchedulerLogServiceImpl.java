/************************************************************************************
 * @File name   :      SchedulerLogServiceImpl.java
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

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.sos.portal.scheduler.annotation.DataSource;
import com.sos.portal.scheduler.data.dao.SchedulerLogDao;
import com.sos.portal.scheduler.domain.SchedulerLogDomain;
import com.sos.portal.scheduler.service.SchedulerLogService;

/**
 *
 */
@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=RuntimeException.class)
@DataSource("fleetDataSource")
public class SchedulerLogServiceImpl extends BaseServiceImpl<SchedulerLogDomain, Long> implements SchedulerLogService {
	private SchedulerLogDao schedulerLogDao;
	
	/** 
	 * overridden:
	 * @Author      :      junjunzhu
	 * @Date        :      2015年5月5日
	 * @see com.sos.portal.scheduler.service.SchedulerLogService#getLastSchedulerByName(java.lang.String)
	**/
	@Override
	@Transactional(readOnly=true,propagation=Propagation.REQUIRED)
	public SchedulerLogDomain getLastSchedulerByName(final String name) {
		Page<SchedulerLogDomain>  page = schedulerLogDao.findPage(new Specification<SchedulerLogDomain>() {
			
			@Override
			public Predicate toPredicate(Root<SchedulerLogDomain> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("name"), name);
			}
		}, new PageRequest(0, 1,new Sort(Direction.DESC, "sourceToDate")));
		List<SchedulerLogDomain> content = page.getContent();
		return CollectionUtils.isEmpty(content)?null:content.get(0);
	}

	/**
	 * @Author      :      junjunzhu
	 *
	 * @Date        :      2015年5月5日
	 *
	 * @param schedulerLogDao the schedulerLogDao to set
	 */
	@Autowired
	public void setSchedulerLogDao(SchedulerLogDao schedulerLogDao) {
		this.jpaRepository = this.schedulerLogDao = schedulerLogDao;
	}

	/** 
	 * overridden:
	 * @Author      :      junjunzhu
	 * @Date        :      2015年5月8日
	 * @see com.sos.portal.scheduler.service.SchedulerLogService#updateStatusByIds(java.lang.Long[],int)
	**/
	@Override
	public void updateStatusByIds(Long[] ids,int status) {
		for (int i = 0; i < ids.length; i++) {
			SchedulerLogDomain domain = jpaRepository.getOne(ids[i]);
			if(domain!=null){
				domain.setStatus(status);
				jpaRepository.save(domain);
			}
		}
	}


	
}
