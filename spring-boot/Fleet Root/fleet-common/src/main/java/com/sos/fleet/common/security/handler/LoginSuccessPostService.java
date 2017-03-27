package com.sos.fleet.common.security.handler;

import com.sos.fleet.common.domain.LoginLogDomain;

public interface LoginSuccessPostService {
	void save(LoginLogDomain loginLogDomain);
}
