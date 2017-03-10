package com.sos.fleet.common.security;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.header.Header;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sos.fleet.common.filter.CaptchaFilter;
import com.sos.fleet.common.filter.SmsFilter;
import com.sos.fleet.common.security.handler.ApplicationAuthenticationFailureHandler;
import com.sos.fleet.common.security.handler.ApplicationAuthenticationSuccessHandler;
import com.sos.fleet.common.settings.SecuritySettings;

@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@Configuration
/*
 * @EnableWebMvcSecurity
 * If Servlet 2.5 container, Please set it.
 * If servlet 3.0, system start will auto-config the annotation. 
 */
@EnableWebMvcSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	private ApplicationAuthenticationSuccessHandler successHandler;
	@Autowired
	private SecuritySettings securitySettings;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired(required=false)
	private SmsFilter smsFilter;
	
	@Autowired(required=false)
	private CaptchaFilter captchaFilter;
	
	/*
	 * @Autowired private SecurityProperties security;
	 */
	private List<Header> createHeaders() {
		List<Header> headers = new ArrayList<Header>(3);
		headers.add(new Header("Cache-Control",
				"no-cache, no-store, max-age=0, must-revalidate"));
		headers.add(new Header("Pragma", "no-cache"));
		headers.add(new Header("Expires", "0"));
		return headers;
	}
	/*
	 * @Override public void configure(AuthenticationManagerBuilder auth) throws
	 * Exception {
	 * auth.inMemoryAuthentication().withUser("admin").password("admin")
	 * .roles("ADMIN", "USER").and().withUser("user").password("user")
	 * .roles("USER"); }
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// @formatter:off
		http.headers().cacheControl().disable().addHeaderWriter(new StaticHeadersWriter(createHeaders()){

			@Override
			public void writeHeaders(HttpServletRequest request,
					HttpServletResponse response) {
				if(!StringUtils.equalsIgnoreCase(RequestMethod.POST.toString(), request.getMethod()) ){
					super.writeHeaders(request, response);
				}
			}
			
		}).and()
		.authorizeRequests().antMatchers(securitySettings.getPermitted()).permitAll()
		.anyRequest().authenticated()
		.and().headers().frameOptions().sameOrigin()		
		.and().rememberMe()
		.key(securitySettings.getRememberTokenKey())
		.rememberMeCookieName(securitySettings.getRememberMeCookie())
		.rememberMeParameter(securitySettings.getRememberMeParameter())
		.authenticationSuccessHandler(successHandler)
		.tokenValiditySeconds(securitySettings.getTokenValiditySeconds())
		.and().formLogin().loginPage("/login").successHandler(successHandler)
		.failureHandler(new ApplicationAuthenticationFailureHandler("/login?error"))
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.and().exceptionHandling().accessDeniedPage("/access?error");
		
		if(smsFilter != null) {
			http.addFilterBefore(smsFilter, UsernamePasswordAuthenticationFilter.class);
		}
		if(captchaFilter != null) {
			http.addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class);
		}
		// @formatter:on
	}

	@Override
	public void init(WebSecurity web) throws Exception {
		super.init(web);
		List<String> list = securitySettings.getIgnored();
		if(!CollectionUtils.isEmpty(list)){
			web.ignoring().antMatchers(list.toArray(new String[list.size()]));
		}
	}

}