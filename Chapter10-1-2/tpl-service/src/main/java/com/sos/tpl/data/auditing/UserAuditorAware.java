package com.sos.tpl.data.auditing;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.sos.tpl.common.util.SecurityUtil;

@Configuration
@EnableJpaAuditing
public class UserAuditorAware implements AuditorAware<Long> {

	@Override
	public Long getCurrentAuditor() {
		
		return SecurityUtil.getUserId();
	}
	
}
