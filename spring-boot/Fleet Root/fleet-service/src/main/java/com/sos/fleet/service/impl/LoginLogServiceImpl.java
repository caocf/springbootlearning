package com.sos.fleet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sos.fleet.common.domain.LoginLogDomain;
import com.sos.fleet.common.security.handler.LoginSuccessPostService;
import com.sos.fleet.data.dao.LoginLogDao;
import com.sos.fleet.service.LoginLogService;
@Service
@Transactional(readOnly=true,rollbackFor = RuntimeException.class)
public class LoginLogServiceImpl extends BaseServiceImpl<LoginLogDomain, Long> implements
		LoginLogService,LoginSuccessPostService {

	private LoginLogDao loginLogDao;
	
	@Autowired
	public LoginLogServiceImpl(LoginLogDao loginLogDao){
		this.jpaRepository = this.loginLogDao=loginLogDao;
	}
}
