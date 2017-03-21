//package com.sos.tpl.modules.sys.dao.ldap.impl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import com.sos.tpl.common.web.mvc.dao.ldap.impl.BaseLdapDaoImpl;
//import com.sos.tpl.common.web.mvc.domain.ldap.UserLdapDomain;
//import com.sos.tpl.modules.sys.dao.ldap.UserLdapDao;
//
//@Component
//public class UserLdapDaoImpl extends BaseLdapDaoImpl<UserLdapDomain, String> implements
//		UserLdapDao {
//
//	@Autowired
//	public UserLdapDaoImpl(@Value("com.sos.tpl.common.web.mvc.domain.ldap.UserLdapDomain")Class<UserLdapDomain> clazz) {
//		super(clazz);
//	}
//
//
//}