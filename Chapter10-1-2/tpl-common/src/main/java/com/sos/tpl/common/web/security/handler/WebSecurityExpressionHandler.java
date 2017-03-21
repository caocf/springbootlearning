package com.sos.tpl.common.web.security.handler;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

import com.sos.tpl.common.web.security.ApplicationSecurityExpression;

public class WebSecurityExpressionHandler extends
		DefaultMethodSecurityExpressionHandler {
	private AuthenticationTrustResolver trustResolver = new AuthenticationTrustResolverImpl();
	private String defaultRolePrefix = "ROLE_";
	@Override
	protected MethodSecurityExpressionOperations createSecurityExpressionRoot(
			Authentication authentication, MethodInvocation invocation) {
		ApplicationSecurityExpression root = new ApplicationSecurityExpression(
				authentication);
		root.setThis(invocation.getThis());
		root.setPermissionEvaluator(getPermissionEvaluator());
		root.setTrustResolver(trustResolver);
		root.setRoleHierarchy(getRoleHierarchy());
		root.setDefaultRolePrefix(defaultRolePrefix);
		return root;
	}
	public void setTrustResolver(AuthenticationTrustResolver trustResolver) {
		this.trustResolver = trustResolver;
	}
	public void setDefaultRolePrefix(String defaultRolePrefix) {
		this.defaultRolePrefix = defaultRolePrefix;
	}

}
