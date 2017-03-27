package com.sos.fleet.service;

import com.sos.fleet.common.domain.UserDomain;

public interface UserService extends BaseService<UserDomain, Long> {
	boolean hasUser(String userName);
	
	void saveFetch(UserDomain user);

	void updateFetch(UserDomain user) throws Exception;
}
