package com.sos.fleet.common.settings;

import javax.annotation.PostConstruct;

import org.apache.commons.pool.impl.GenericKeyedObjectPool;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.pool.validation.DefaultDirContextValidator;

import com.sos.fleet.common.settings.LdapSettings.PathPoolingContextSource;


@Configuration
@ConditionalOnProperty("ldap.user.url")
@ConfigurationProperties(prefix="ldap.user")
public class UserLdapSettings {
	private String url;
	private String userDn;
	private String signword;
//	private boolean enablePool;
	private GenericKeyedObjectPool<?,?> keyedObjectPool;
	private static UserLdapSettings LDAP_SETTINGS;
	@PostConstruct
	private void init(){
		LDAP_SETTINGS = this;
	}
	
	public static UserLdapSettings instance(){
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
	
	@Bean
	public ContextSource userContextSource(){
		LdapContextSource lcs = new LdapContextSource();
		lcs.setUrl(getUrl());
		lcs.setUserDn(getUserDn());
		lcs.setPassword(getSignword());
		lcs.setPooled(false);
		lcs.afterPropertiesSet();
		return lcs;
	}
	@Bean
	public ContextSource userPoolingContextSource(){
		PathPoolingContextSource pcs = new PathPoolingContextSource();
		pcs.setContextSource(userContextSource());
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
	public LdapTemplate userLdapTemplate(){
		LdapTemplate lt = new LdapTemplate();
		/*if(enablePool){
			lt.setContextSource(userPoolingContextSource());
		}else{
			lt.setContextSource(userContextSource());
		}*/
		lt.setContextSource(userPoolingContextSource());
		return lt;
	}

	public String getSignword() {
		return signword;
	}

	public void setSignword(String signword) {
		this.signword = signword;
	}

	public GenericKeyedObjectPool<?, ?> getKeyedObjectPool() {
		return keyedObjectPool;
	}

	public void setKeyedObjectPool(GenericKeyedObjectPool<?, ?> keyedObjectPool) {
		this.keyedObjectPool = keyedObjectPool;
	}

/*	public boolean isEnablePool() {
		return enablePool;
	}

	public void setEnablePool(boolean enablePool) {
		this.enablePool = enablePool;
	}*/
}
