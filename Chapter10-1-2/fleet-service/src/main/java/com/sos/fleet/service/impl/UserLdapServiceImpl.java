package com.sos.fleet.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.sos.fleet.common.constants.StatusKeys;
import com.sos.fleet.common.domain.ldap.UserLdapDomain;
import com.sos.fleet.common.exception.ServiceException;
import com.sos.fleet.common.security.UserDetailsMapper;
import com.sos.fleet.common.util.JsonUtil;
import com.sos.fleet.data.ldap.UserLdapDao;
import com.sos.fleet.data.util.LdapUtil;
import com.sos.fleet.service.UserLdapService;

@Service
public class UserLdapServiceImpl extends BaseLdapServiceImpl<UserLdapDomain, String> implements
		UserLdapService,UserDetailsMapper<UserLdapDomain> {
	private UserLdapDao userLdapDao;
	
	@Autowired
	public UserLdapServiceImpl(UserLdapDao userLdapDao){
		this.baseDao = this.userLdapDao = userLdapDao;
	}

	@Override
	public UserLdapDomain mapping(String username) {
		List<UserLdapDomain> domains =  userLdapDao.findByProperty("uid", username);
		if(CollectionUtils.isEmpty(domains)){
			return null;
		}
		return domains.get(0);
	}

	@Override
	public void resetPwd(String uid) {
		// TODO Auto-generated method stub
		UserLdapDomain ldapuser = this.mapping(uid);
		ldapuser.setUserPassword(uid);
		ldapuser.setFirstLogin(StatusKeys.LDAP_FIRST_LOGIN);
		this.userLdapDao.update(ldapuser);
	}

	@Override
	public void modifyPwd(String userName, String oldPassword, String password) {
		// TODO Auto-generated method stub
		if(this.validatePassword(oldPassword, userName)) {
			UserLdapDomain ldapuser = this.mapping(userName);
			ldapuser.setFirstLogin(StatusKeys.LDAP_NOT_FIRST_LOGIN);
			ldapuser.setUserPassword(password);
			this.userLdapDao.update(ldapuser);
		}else {
			throw new ServiceException("old password verified failed");
		}
	}

	@Override
	public void firstLogin(String userName, String password) {
		// TODO Auto-generated method stub
		UserLdapDomain ldapuser = this.mapping(userName);
		ldapuser.setFirstLogin(StatusKeys.LDAP_NOT_FIRST_LOGIN);
		ldapuser.setUserPassword(password);
		this.userLdapDao.update(ldapuser);
	}
	
}
