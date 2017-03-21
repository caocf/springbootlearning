package com.sos.tpl.common.web.security;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.stereotype.Component;

import com.sos.tpl.common.web.mvc.domain.UserDomain;
import com.sos.tpl.common.web.mvc.domain.ldap.UserLdapDomain;

@Component
public class ApplicationUserDetailsMapper extends LdapUserDetailsMapper{

	@Autowired
	ApplicationContext applicationContext;
	
	@Override
	public UserDetails mapUserFromContext(DirContextOperations ctx,
			String username, Collection<? extends GrantedAuthority> authorities) {
		username = StringUtils.lowerCase(username);
		LdapUserDetails  ud = (LdapUserDetails) super.mapUserFromContext(ctx, username, authorities);
		return getApplicationUserDetails(ud, ud.getUsername());
	}
	@SuppressWarnings("rawtypes")
	private ApplicationUserDetails getApplicationUserDetails(LdapUserDetails  ud,String userName){
		Map<String,UserDetailsMapper> map= applicationContext.getBeansOfType(UserDetailsMapper.class);
		Iterator<UserDetailsMapper> it = map.values().iterator();
		UserLdapDomain uld = null;
		UserDomain userDomain=null;
		for(;it.hasNext();){
			UserDetailsMapper udm = it.next();
			Object userObject = udm.mapping(userName);
			if(userObject instanceof UserLdapDomain){
				uld = (UserLdapDomain) userObject;
			}else if (userObject instanceof UserDomain){
				userDomain =  (UserDomain) userObject;
			}
		}
		ApplicationUserDetails  rs= new ApplicationUserDetails(ud,uld,userDomain);
		return rs;
	}

}
