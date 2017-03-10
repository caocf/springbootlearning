package com.sos.fleet.common.security;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import com.sos.fleet.common.settings.EnvironmentSettings;

public class ApplicationSecurityExpression extends SecurityExpressionRoot implements MethodSecurityExpressionOperations{
	private Object filterObject;
	private Object returnObject;
	private Object target;
	
	
	public ApplicationSecurityExpression(Authentication authentication) {
		super(authentication);
	}
	
	public boolean denyOnReadOnlyEnv(){
		return !EnvironmentSettings.ENABLE_DREAD_ONLY;
	}

	@Override
	public void setFilterObject(Object filterObject) {
		this.filterObject = filterObject;
	}

	@Override
	public Object getFilterObject() {
		return filterObject;
	}

	@Override
	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}

	@Override
	public Object getReturnObject() {
		return returnObject;
	}

	@Override
	public Object getThis() {
		return target;
	}

	public void setThis(Object target) {
		this.target = target;
	}

}
