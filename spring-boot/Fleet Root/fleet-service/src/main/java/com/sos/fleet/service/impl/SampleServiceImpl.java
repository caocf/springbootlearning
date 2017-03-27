package com.sos.fleet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sos.fleet.common.domain.SampleAllDomain;
import com.sos.fleet.data.dao.SampleDao;
import com.sos.fleet.service.SampleService;

@Service
@Transactional(readOnly=true,rollbackFor = RuntimeException.class)
public class SampleServiceImpl extends BaseServiceImpl<SampleAllDomain, Long> implements SampleService {
	protected SampleDao sampleDao;
	@Autowired
	public void setSampleDao(SampleDao sampleDao){
		this.jpaRepository = this.sampleDao = sampleDao;
	}
}
