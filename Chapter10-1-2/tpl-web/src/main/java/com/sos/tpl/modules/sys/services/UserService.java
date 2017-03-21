package com.sos.tpl.modules.sys.services;

import com.sos.tpl.common.web.mvc.domain.UserDomain;
import com.sos.tpl.common.web.mvc.service.base.BaseService;

public interface UserService extends BaseService<UserDomain, Long> {
	boolean hasUser(String userName);
	
	void saveFetch(UserDomain user);

	void updateFetch(UserDomain user) throws Exception;
}