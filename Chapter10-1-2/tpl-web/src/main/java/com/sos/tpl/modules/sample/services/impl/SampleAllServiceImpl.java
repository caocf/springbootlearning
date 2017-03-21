package com.sos.tpl.modules.sample.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sos.tpl.common.web.mvc.service.base.impl.BaseServiceImpl;
import com.sos.tpl.modules.sample.domain.SampleAllDomain;
import com.sos.tpl.modules.sample.services.SampleAllService;

@Service
@Transactional(readOnly=true,rollbackFor = RuntimeException.class)
public class SampleAllServiceImpl extends BaseServiceImpl<SampleAllDomain, Long> implements
		SampleAllService  {
//	@Autowired
//	protected SampleAllDao sampleAllDao;
//	
	
//	public void setSampleDao(SampleAllDao sampleAllDao){
//		this.jpaRepository = this.sampleAllDao = sampleAllDao;
//	}
	
	
}