package com.sos.tpl.common.web.mvc.dao.ldap.impl;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

import java.io.Serializable;
import java.util.List;

import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.ldap.LdapName;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.AuthenticationException;
import org.springframework.ldap.core.AuthenticatedLdapEntryContextMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapEntryIdentification;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.support.LdapNameBuilder;

import com.sos.tpl.common.util.LdapUtil;
import com.sos.tpl.common.web.mvc.dao.ldap.BaseLdapDao;
import com.sos.tpl.common.web.mvc.domain.ldap.BaseLdapDomain;

public class BaseLdapDaoImpl<T extends BaseLdapDomain<ID>, ID extends Serializable>
		implements BaseLdapDao<T, ID> {

	private Class<T> clazz;
	private String baseDn;
	private static Log log = LogFactory.getLog(BaseLdapDaoImpl.class);

	public BaseLdapDaoImpl(Class<T> clazz) {
		super();
		this.clazz = clazz;
		String base = clazz.getAnnotation(Entry.class).base();
		log.debug("base dn: " + base);
		baseDn = base;
		ldapName = LdapNameBuilder.newInstance(base).build();
	}

	protected Name buildDn(String uid) {
		return LdapNameBuilder.newInstance(ldapName).add("uid", uid).build();
	}

	@Autowired
	protected LdapTemplate ldapTemplate;
	private LdapName ldapName;

	@Override
	public void setBaseLdapPath(LdapName baseLdapPath) {
		this.ldapName = baseLdapPath;
	}

	@Override
	public T save(T T) {
		T.setRdn(buildDn(T.getUid().toString()));
		ldapTemplate.create(T);
		return T;
	}

	@Override
	public T update(T T) {
		T.setRdn(buildDn(T.getUid().toString()));
		ldapTemplate.update(T);
		return T;
	}

	@Override
	public T get(ID id) {
		return ldapTemplate.findOne(
				query().base(ldapName).where("uid").is((String) id), clazz);
	}

	@Override
	public void delete(T T) {
		T.setRdn(buildDn(T.getUid().toString()));
		ldapTemplate.delete(T);
	}

	@Override
	public void delete(ID id) {
		ldapTemplate.unbind(buildDn(id.toString()));
	}

	@Override
	public void delete(Name rnm) {
		ldapTemplate.unbind(rnm);
	}

	@Override
	public void logicDelete(T entity) {
		logicDelete(entity.getUid());
	}

	@Override
	public void logicDelete(ID id) {
		logicDelete(buildDn(id.toString()));
	}

	@Override
	public void logicDelete(Name rnm) {
		DirContextOperations context = ldapTemplate.lookupContext(rnm);
		context.setAttributeValue("isenabled", "false");
		ldapTemplate.modifyAttributes(context);
	}

	@Override
	public List<T> findByProperty(String propertyName, Object propertyValue) {
		return ldapTemplate.find(
				query().base(ldapName).where(propertyName)
						.is(String.valueOf(propertyValue)), clazz);
	}

	@Override
	public T updatePropertiesNonNull(T entity) {
		ldapTemplate.modifyAttributes(buildDn((String) entity.getUid()),
				LdapUtil.buildModificationItem(entity, true));
		return entity;
	}

	@Override
	public boolean validatePassword(String password, String uid) {
		DirContextOperations dco = null;
		
		try {
			dco = ldapTemplate
			.authenticate(
					query().base(ldapName).where("uid").is(uid),
					password,
					new AuthenticatedLdapEntryContextMapper<DirContextOperations>() {

						@Override
						public DirContextOperations mapWithContext(
								DirContext ctx,
								LdapEntryIdentification ldapEntryIdentification) {
							try {
								return (DirContextOperations) ctx
										.lookup(ldapEntryIdentification
												.getRelativeName());
							} catch (NamingException e) {
								throw new RuntimeException(
										"Failed to lookup "
												+ ldapEntryIdentification
														.getRelativeName(),
										e);
							}

						}
					});
		} catch (AuthenticationException e) {
			// TODO: handle exception
			log.debug("AuthenticationException::::"+e.getMessage());
		}
		
		return dco!=null;
	}

	public String getBaseDn() {
		return baseDn;
	}

}