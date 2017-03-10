package com.sos.fleet.service;

import com.sos.fleet.common.domain.ldap.UserLdapDomain;

public interface UserLdapService extends BaseLdapService<UserLdapDomain, String> {

	void resetPwd(String uid);

	void modifyPwd(String userName, String oldPassword, String password);

	void firstLogin(String userName, String password);
}
