package com.sos.tpl.common.web.security.ldap;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.LdapUserDetailsService;

import com.sos.tpl.common.settings.LdapSettings;
import com.sos.tpl.common.web.security.ApplicationUserDetailsMapper;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class AuthenticationSecurity extends
		GlobalAuthenticationConfigurerAdapter { 
	static final Log log = LogFactory.getLog(AuthenticationSecurity.class);
	@Autowired
	LdapSettings ldapSettings;

	@Resource(name="securityContextSource")
	ContextSource contextSource;
	@Autowired
	private ApplicationUserDetailsMapper userDetailsMapper;

	@Override
	public void init(AuthenticationManagerBuilder auth) throws Exception {
		DefaultLdapAuthoritiesPopulator dlap = new DefaultLdapAuthoritiesPopulator(
				contextSource, ldapSettings.getGroupSearchBase());
		dlap.setGroupRoleAttribute(ldapSettings.getGroupRoleAttribute());
		dlap.setGroupSearchFilter(ldapSettings.getGroupSearchFilter());
		FilterBasedLdapUserSearch userSearch = new FilterBasedLdapUserSearch(
				ldapSettings.getUserSearchBase(),
				ldapSettings.getUserSearchFilter(),
				(BaseLdapPathContextSource) contextSource);
		LdapUserDetailsService userDetailsService = new LdapUserDetailsService(
				userSearch, dlap);
		userDetailsService.setUserDetailsMapper(userDetailsMapper);
		auth.ldapAuthentication().userDetailsContextMapper(userDetailsMapper)
				.userDnPatterns(ldapSettings.getUserDnPatterns())
				.userSearchBase(ldapSettings.getUserSearchBase())
				.userSearchFilter(ldapSettings.getUserSearchFilter())
				.contextSource((BaseLdapPathContextSource) contextSource)
				.ldapAuthoritiesPopulator(dlap).and()
				.userDetailsService(userDetailsService);
	}

}