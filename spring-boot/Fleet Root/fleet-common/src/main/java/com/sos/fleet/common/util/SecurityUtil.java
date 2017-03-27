package com.sos.fleet.common.util;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.sos.fleet.common.domain.FleetDomain;
import com.sos.fleet.common.domain.UserDomain;
import com.sos.fleet.common.domain.ldap.UserLdapDomain;
import com.sos.fleet.common.security.ApplicationUserDetails;

public class SecurityUtil {
	private static final String ROLE_PREFIX = "ROLE_";
	static Log log = LogFactory.getLog(SecurityUtil.class);
	
	public static Authentication getAuthentication(){
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
	public static UserDetails getUserDetails(){
		Authentication authentication = getAuthentication();
		return getUserDetails(authentication);
	}
	public static String getUserName() {
		UserDetails userDetails = getUserDetails();
		return userDetails==null?null:userDetails.getUsername();
	}
	public static Long getUserId() {
		UserDomain user = getUser();
		return user==null?null:user.getId();
	}
	public static UserDomain getUser(){
		ApplicationUserDetails userDetails = getApplicationUserDetails();
		return userDetails==null?null:userDetails.getUserDomain();
	}
	public static UserLdapDomain getLdapUser(){
		ApplicationUserDetails userDetails = getApplicationUserDetails();
		return userDetails==null?null:userDetails.getUserLdapDomain();
	}
	
	public static FleetDomain getFleet(){
		UserDomain user = getUser();
		FleetDomain fleet = user==null?null:user.getFleetDomain();
		return fleet;
	}
	public static Long getFleetId(){
		FleetDomain fleet = getFleet();
		return fleet==null?null:fleet.getId();
	}
	public static ApplicationUserDetails getApplicationUserDetails(){
		return (ApplicationUserDetails) getUserDetails();
	}
	
	public static String[] getUserRoles(){
		UserDetails ud =getUserDetails();
		if(ud==null){
			return null;
		}
		Collection<? extends GrantedAuthority> colls = ud.getAuthorities();
		int len = colls.size();
		String[] roles = new String[len];
		int i=0;
		for (Iterator<? extends GrantedAuthority> iterator = colls.iterator(); iterator.hasNext();i++) {
			GrantedAuthority grantedAuthority =  iterator
					.next();
			String role = grantedAuthority.getAuthority();
			roles[i]=role.indexOf(ROLE_PREFIX)>-1?role.substring(ROLE_PREFIX.length()):role;
		}
		return roles;
	}
	public static String getUserRole(){
		 String[] roles = getUserRoles();
		return ArrayUtils.isEmpty(roles)?null:roles[0];
	}
	
	
	
	
	
	public static UserDetails getUserDetails(Authentication authentication){
		if(authentication!=null&&authentication.getPrincipal()!=null&&authentication.getPrincipal() instanceof UserDetails){
			return (UserDetails) authentication.getPrincipal();
		}
		return null;
	}
	public static String getUserName(Authentication authentication) {
		UserDetails userDetails = getUserDetails(authentication);
		return userDetails==null?null:userDetails.getUsername();
	}
	public static UserDomain getUser(Authentication authentication){
		ApplicationUserDetails userDetails = getApplicationUserDetails(authentication);
		return userDetails==null?null:userDetails.getUserDomain();
	}
	public static UserLdapDomain getLdapUser(Authentication authentication){
		ApplicationUserDetails userDetails = getApplicationUserDetails(authentication);
		return userDetails==null?null:userDetails.getUserLdapDomain();
	}
	
	public static ApplicationUserDetails getApplicationUserDetails(Authentication authentication){
		return (ApplicationUserDetails) getUserDetails(authentication);
	}
	
	public static String[] getUserRoles(Authentication authentication){
		UserDetails ud = getUserDetails(authentication);
		if(ud==null){
			return null;
		}
		Collection<? extends GrantedAuthority> colls = ud.getAuthorities();
		int len = colls.size();
		String[] roles = new String[len];
		int i=0;
		for (Iterator<? extends GrantedAuthority> iterator = colls.iterator(); iterator.hasNext();i++) {
			GrantedAuthority grantedAuthority =  iterator
					.next();
			String role =grantedAuthority.getAuthority();
			roles[i]=role.indexOf(ROLE_PREFIX)>-1?role.substring(ROLE_PREFIX.length()):role;
		}
		return roles;
	}
	public static String getUserRole(Authentication authentication){
		 String[] roles = getUserRoles(authentication);
		return ArrayUtils.isEmpty(roles)?null:roles[0];
	}
}
