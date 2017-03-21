package com.sos.tpl.common.web.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sos.tpl.common.constants.StatusKeys;
import com.sos.tpl.common.settings.SecuritySettings;
import com.sos.tpl.common.util.SecurityUtil;
import com.sos.tpl.common.util.UrlPathUtil;
import com.sos.tpl.common.web.mvc.domain.ldap.UserLdapDomain;

/**
 * @see com.sos.fleet.common.web.ApplicationMvcConfigurerAdapter.addInterceptors();
 * @author JunjunZhu
 *
 */
public class FirstLoginInterceptor extends HandlerInterceptorAdapter {
	Log log = LogFactory.getLog(getClass());
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		log.debug("request uri: " + UrlPathUtil.getRequestUri(request));
		
		UserLdapDomain userDomain = SecurityUtil.getLdapUser();
		if(userDomain==null){
			return true;
		}
		String redirect;
		if(StatusKeys.LDAP_FIRST_LOGIN.equals( userDomain.getFirstLogin())){
			if(!UrlPathUtil.getServletPath(request).equals(SecuritySettings.instance().getFirstLoginUrl())
					&&!"/firstLogin".equals(UrlPathUtil.getServletPath(request))){
				redirect = UrlPathUtil.getContextPath(request)+SecuritySettings.instance().getFirstLoginUrl();
			}else{
				return true;
			}
			log.debug("request uri: " + UrlPathUtil.getRequestUri(request)+" allowed by first login Interceptor, redirect to "+redirect);
		}else{
			if(UrlPathUtil.getServletPath(request).equals(SecuritySettings.instance().getFirstLoginUrl())){
				redirect = UrlPathUtil.getContextPath(request);
			}else{
				return true;
			}
		}
		response.sendRedirect(redirect);
 		return false;
	}

}
