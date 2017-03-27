package com.sos.fleet.common.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetails;

import com.sos.fleet.common.domain.UserDomain;
import com.sos.fleet.common.domain.ldap.UserLdapDomain;

public class ApplicationUserDetails implements UserDetails {

	private LdapUserDetails ldapUserDetails;
	private UserLdapDomain userLdapDomain;
	private UserDomain userDomain;
	public ApplicationUserDetails(LdapUserDetails ldapUserDetails,
			UserLdapDomain userLdapDomain, UserDomain userDomain) {
		super();
		this.ldapUserDetails = ldapUserDetails;
		this.userLdapDomain = userLdapDomain;
		this.userDomain = userDomain;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return ldapUserDetails.getAuthorities();
	}

	@Override
	public String getPassword() {
		return ldapUserDetails.getPassword();
	}

	@Override
	public String getUsername() {
		return  ldapUserDetails.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return ldapUserDetails.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return ldapUserDetails.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return ldapUserDetails.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return ldapUserDetails.isEnabled();
	}

	public LdapUserDetails getLdapUserDetails() {
		return ldapUserDetails;
	}

	public UserLdapDomain getUserLdapDomain() {
		return userLdapDomain;
	}

	public void fill(LdapUserDetails userDetails){
		this.ldapUserDetails = userDetails;
		
	}

	public UserDomain getUserDomain() {
		return userDomain;
	}
}
