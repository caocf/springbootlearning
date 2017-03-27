package com.sos.portal.scheduler.task;

import java.util.Iterator;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.util.ClassUtils;

import com.sos.portal.scheduler.quartz.impl.QuartzJobImpl;
import com.sos.portal.scheduler.settings.SchedulerSettings;
import com.sos.portal.scheduler.work.TaskWork;

@Configuration
public class TaskConfiguration {
	Log log = LogFactory.getLog(getClass());
	@Autowired
	ApplicationContext applicationContext;

	@Autowired
	SchedulerSettings schedulerSettings;

	@Bean
	@SuppressWarnings({ "rawtypes"})
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean sfb = new SchedulerFactoryBean();
		Map<String, SpringBootSchedulerTask> map = applicationContext
				.getBeansOfType(SpringBootSchedulerTask.class);
		Iterator<String> it = map.keySet().iterator();
		Trigger[] triggers = new Trigger[map.size()];
		try {
			for (int i = 0; it.hasNext(); i++) {
				String key = it.next();
				SpringBootSchedulerTask st = map.get(key);
				QuartzJobImpl qbi = new QuartzJobImpl();
				String name = ClassUtils.getMethod(st.getClass(), "registerTaskWork").getAnnotation(Bean.class).name()[0];
				log.debug("registerTaskWork name: " + name);
				qbi.setTaskWork(applicationContext.getBean(name
						, TaskWork.class));
				MethodInvokingJobDetailFactoryBean mijdfb = new MethodInvokingJobDetailFactoryBean();
				mijdfb.setTargetObject(qbi);
				mijdfb.setTargetMethod("doTask");
				mijdfb.setConcurrent(schedulerSettings.isConcurrent());
				mijdfb.setBeanName(st.getTaskName()+"JobDetail");
				mijdfb.afterPropertiesSet();
				CronTriggerFactoryBean ctfb = new CronTriggerFactoryBean();
				ctfb.setJobDetail(mijdfb.getObject());
				ctfb.setCronExpression(st.getCronExpression());
				ctfb.setBeanName(st.getTaskName()+"Trigger");
				ctfb.afterPropertiesSet();
				triggers[i] = ctfb.getObject();
			}
			sfb.setTriggers(triggers);
			sfb.afterPropertiesSet();
		} catch (Exception e) {
			log.error(e,e);
		}
		return sfb;
	}

}
