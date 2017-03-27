package com.sos.portal.scheduler.data;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiObjectFactoryBean;

import com.sos.portal.scheduler.settings.DataSourceSettings;
import com.sos.portal.scheduler.settings.DataSourceSettings.Jndi;
import com.sos.portal.scheduler.util.DynamicDataSource;
import com.sos.portal.scheduler.util.JsonUtil;

@Configuration
@EnableJpaRepositories(repositoryFactoryBeanClass=SchedulerRepositoryFactoryBean.class)
@EnableJpaAuditing
public class JpaConfig {
	Log log = LogFactory.getLog(getClass());
	@Autowired
	private DataSourceSettings dss;
	
	@Bean
	public DynamicDataSource dynamicDataSource(){
		DynamicDataSource dds = new DynamicDataSource();
		Map<Object, Object> targetDataSources = new HashMap<Object, Object>(dss.getJndis().size());
		for (int i = 0; i < dss.getJndis().size(); i++) {
			Jndi jndi = dss.getJndis().get(i);
			log.debug("jndi: " +JsonUtil.getJson(jndi));
			JndiObjectFactoryBean jofb = new JndiObjectFactoryBean();
			jofb.setJndiName(jndi.getName());
			try {
				jofb.afterPropertiesSet();
			}  catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			targetDataSources.put(jndi.getId(), jofb.getObject());
			if(jndi.isDefault()){
				dds.setDefaultTargetDataSource(jofb.getObject());
			}
		}
		dds.setTargetDataSources(targetDataSources);
		dds.afterPropertiesSet();
		return dds;
	}
}
