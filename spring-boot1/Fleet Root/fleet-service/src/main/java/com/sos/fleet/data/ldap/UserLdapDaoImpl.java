package com.sos.fleet.data.ldap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sos.fleet.common.domain.ldap.UserLdapDomain;
import com.sos.fleet.data.ldap.base.BaseLdapDaoImpl;

@Component
public class UserLdapDaoImpl extends BaseLdapDaoImpl<UserLdapDomain, String> implements
		UserLdapDao {

	@Autowired
	public UserLdapDaoImpl(@Value("com.sos.fleet.common.domain.ldap.UserLdapDomain")Class<UserLdapDomain> clazz) {
		super(clazz);
	}


}
