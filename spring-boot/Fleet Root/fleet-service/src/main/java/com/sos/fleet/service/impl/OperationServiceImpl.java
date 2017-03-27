package com.sos.fleet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sos.fleet.common.domain.OperationLogDomain;
import com.sos.fleet.common.util.OperationUtil.OperationService;
import com.sos.fleet.data.dao.OperationLogDao;
import com.sos.fleet.service.OperationLogService;

@Service
@Transactional(readOnly=true,rollbackFor = RuntimeException.class)
public class OperationServiceImpl extends BaseServiceImpl<OperationLogDomain, Long> implements
		OperationLogService,OperationService {

	private OperationLogDao operationLogDao;
	
	@Autowired
	public void setOperationLogDao(OperationLogDao operationLogDao) {
		this.jpaRepository = this.operationLogDao = operationLogDao;
	}

	@Override
	@Transactional
	public void log(OperationLogDomain operationLogDomain) {
		operationLogDao.save(operationLogDomain);
	}

}
