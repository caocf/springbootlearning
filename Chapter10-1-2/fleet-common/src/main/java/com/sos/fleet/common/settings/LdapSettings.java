package com.sos.fleet.common.settings;

import javax.annotation.PostConstruct;
import javax.naming.directory.DirContext;

import org.apache.commons.pool.impl.GenericKeyedObjectPool;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.pool.factory.PoolingContextSource;
import org.springframework.ldap.pool.validation.DefaultDirContextValidator;


@Configuration
@ConfigurationProperties(prefix="ldap.security")
public class LdapSettings {
	private String url;
	private String userDn;
	private String signword;
	private String userBase;
	private String[] userDnPatterns;
	private String groupSearchBase;
	private String groupRoleAttribute;
	private String groupSearchFilter;
	private String userSearchFilter;
	private String userSearchBase;
//	private boolean enablePool;
	private GenericKeyedObjectPool<?,?> keyedObjectPool;
	private static LdapSettings LDAP_SETTINGS;
	@PostConstruct
	private void init(){
		LDAP_SETTINGS = this;
	}
	
	public static LdapSettings instance(){
		return LDAP_SETTINGS;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUserDn() {
		return userDn;
	}
	public void setUserDn(String userDn) {
		this.userDn = userDn;
	}
	
	public String[] getUserDnPatterns() {
		return userDnPatterns;
	}
	public void setUserDnPatterns(String[] userDnPatterns) {
		this.userDnPatterns = userDnPatterns;
	}
	public String getGroupSearchBase() {
		return groupSearchBase;
	}
	public void setGroupSearchBase(String groupSearchBase) {
		this.groupSearchBase = groupSearchBase;
	}
	public String getGroupRoleAttribute() {
		return groupRoleAttribute;
	}
	public void setGroupRoleAttribute(String groupRoleAttribute) {
		this.groupRoleAttribute = groupRoleAttribute;
	}
	public String getGroupSearchFilter() {
		return groupSearchFilter;
	}
	public void setGroupSearchFilter(String groupSearchFilter) {
		this.groupSearchFilter = groupSearchFilter;
	}

	public String getUserBase() {
		return userBase;
	}

	public void setUserBase(String userBase) {
		this.userBase = userBase;
	}

	public String getUserSearchFilter() {
		return userSearchFilter;
	}

	public void setUserSearchFilter(String userSearchFilter) {
		this.userSearchFilter = userSearchFilter;
	}

	public String getUserSearchBase() {
		return userSearchBase;
	}

	public void setUserSearchBase(String userSearchBase) {
		this.userSearchBase = userSearchBase;
	}
	
	public static class PathPoolingContextSource extends PoolingContextSource implements
		BaseLdapPathContextSource {
		@Override
		public DirContext getContext(String principal, String credentials) {
			return this.getContextSource().getContext(principal, credentials);
		}
	}

	/*@Bean
	public ContextSource securityContextSource(){
		DefaultSpringSecurityContextSource dsscs = new DefaultSpringSecurityContextSource(
				getUrl());
		dsscs.setUserDn(getUserDn());
		dsscs.setPassword(getSignword());
		dsscs.afterPropertiesSet();
		return dsscs;
		PathPoolingContextSource pcs = new PathPoolingContextSource();
		pcs.setContextSource(serviceContextSource());
		pcs.setDirContextValidator(new DefaultDirContextValidator());
		pcs.setMaxActive(keyedObjectPool.getMaxActive());
		pcs.setMaxIdle(keyedObjectPool.getMaxIdle());
		pcs.setMaxTotal(keyedObjectPool.getMaxTotal());
		pcs.setMaxWait(keyedObjectPool.getMaxWait());
		pcs.setMinEvictableIdleTimeMillis(keyedObjectPool.getMinEvictableIdleTimeMillis());
		pcs.setMinIdle(keyedObjectPool.getMinIdle());
		pcs.setNumTestsPerEvictionRun(keyedObjectPool.getNumTestsPerEvictionRun());
		pcs.setTestOnBorrow(keyedObjectPool.getTestOnBorrow());
		pcs.setTestOnReturn(keyedObjectPool.getTestOnReturn());
		pcs.setTestWhileIdle(keyedObjectPool.getTestWhileIdle());
		pcs.setTimeBetweenEvictionRunsMillis(keyedObjectPool.getTimeBetweenEvictionRunsMillis());
		pcs.setWhenExhaustedAction(keyedObjectPool.getWhenExhaustedAction());
		return pcs;
	}*/
	public ContextSource serviceContextSource(){
		LdapContextSource lcs = new LdapContextSource();
		lcs.setUrl(getUrl());
		lcs.setUserDn(getUserDn());
		lcs.setPassword(getSignword());
		lcs.setPooled(false);
		lcs.afterPropertiesSet();
		return lcs;
	}
	@Bean
	public ContextSource securityContextSource(){
		PathPoolingContextSource pcs = new PathPoolingContextSource();
		pcs.setContextSource(serviceContextSource());
		pcs.setDirContextValidator(new DefaultDirContextValidator());
		pcs.setMaxActive(keyedObjectPool.getMaxActive());
		pcs.setMaxIdle(keyedObjectPool.getMaxIdle());
		pcs.setMaxTotal(keyedObjectPool.getMaxTotal());
		pcs.setMaxWait(keyedObjectPool.getMaxWait());
		pcs.setMinEvictableIdleTimeMillis(keyedObjectPool.getMinEvictableIdleTimeMillis());
		pcs.setMinIdle(keyedObjectPool.getMinIdle());
		pcs.setNumTestsPerEvictionRun(keyedObjectPool.getNumTestsPerEvictionRun());
		pcs.setTestOnBorrow(keyedObjectPool.getTestOnBorrow());
		pcs.setTestOnReturn(keyedObjectPool.getTestOnReturn());
		pcs.setTestWhileIdle(keyedObjectPool.getTestWhileIdle());
		pcs.setTimeBetweenEvictionRunsMillis(keyedObjectPool.getTimeBetweenEvictionRunsMillis());
		pcs.setWhenExhaustedAction(keyedObjectPool.getWhenExhaustedAction());
		return pcs;
	}
	@Bean
	@ConditionalOnMissingBean(UserLdapSettings.class)
	public LdapTemplate securityLdapTemplate(){
		LdapTemplate lt = new LdapTemplate();
		/*if(enablePool){
			lt.setContextSource(securityPoolingContextSource());
		}else{
			lt.setContextSource(securityContextSource());
		}*/
		lt.setContextSource(securityContextSource());
		return lt;
	}
	
	public String getSignword() {
		return signword;
	}

	public void setSignword(String signword) {
		this.signword = signword;
	}

	public GenericKeyedObjectPool<?,?> getKeyedObjectPool() {
		return keyedObjectPool;
	}

	public void setKeyedObjectPool(GenericKeyedObjectPool<?,?> keyedObjectPool) {
		this.keyedObjectPool = keyedObjectPool;
	}

	/*public boolean isEnablePool() {
		return enablePool;
	}

	public void setEnablePool(boolean enablePool) {
		this.enablePool = enablePool;
	}*/
}
