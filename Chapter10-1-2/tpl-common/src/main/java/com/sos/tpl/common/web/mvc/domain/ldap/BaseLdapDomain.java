package com.sos.tpl.common.web.mvc.domain.ldap;

import java.io.Serializable;

import javax.naming.Name;

public abstract interface  BaseLdapDomain<ID extends Serializable> {
	public abstract ID getUid();
	public abstract void setRdn(Name rnd);
}
