/************************************************************************************
 * @File name   :      WorkAop.java
 *
 * @Author      :      junjunzhu
 *
 * @Date        :      2015年5月5日
 *
 * @Copyright Notice: 
 * Copyright (c) 2012 Shanghai OnStar, Inc. All  Rights Reserved.
 * This software is published under the terms of the Shanghai OnStar Software
 * License version 1.0, a copy of which has been included with this
 * distribution in the LICENSE.txt file.
 * 
 * 
 * ----------------------------------------------------------------------------------
 * Date								Who					Version				Comments
 * 2015年5月5日 下午6:13:34			junjunzhu			1.0				Initial Version
 ************************************************************************************/
package com.sos.portal.scheduler.aop;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import com.sos.portal.scheduler.domain.SchedulerLogDomain;
import com.sos.portal.scheduler.dto.MailType;
import com.sos.portal.scheduler.exception.SchedulerFailureException;
import com.sos.portal.scheduler.mail.BaseMailDto;
import com.sos.portal.scheduler.mail.FreemarkConfigFactory;
import com.sos.portal.scheduler.rest.RestClient;
import com.sos.portal.scheduler.settings.EmailSettings;
import com.sos.portal.scheduler.util.JsonUtil;
import com.sos.portal.scheduler.util.SchedulerUtil;
import com.sos.portal.scheduler.work.TaskWork;

/**
 *
 */
@Component
@Aspect
public class WorkAop implements Ordered {
	protected static final Log log = LogFactory.getLog(WorkAop.class);
	@Autowired
	private RestClient restSupport ;
	@Value("${ws.sendMail}")
	private String restfulSendEmailURL;
/*	@Value("${email.send.notice.to.address}")
	private String emailSendNoticeAddress;
	@Value("${email.send.error.to.address}")
	private String emailSendErrorAddress;
	@Value("${email.send.subject.format}")
	private String emailSubjectFormat;
	@Value("${email.sendFormAddress}")
	private String sendFormAddress;
	
	@Value("${email.send.notice.enabled}")
	private String enabledEmailNotice;
	@Value("${email.send.success.enabled}")
	private String enabledEmailSuccess;*/
	
	@Autowired
	EmailSettings emailSttinggs;
	
	@Pointcut("execution(* com.sos.portal.scheduler.work.*.*Task(..))")
	public void taskMethod(){	}
	@Pointcut("execution(* com.sos.portal.scheduler.work.*.*Rerun(..))")
	public void rerunMethod(){	}
	@Before("taskMethod()")
	public void beforeTask(JoinPoint jp) {
		log.debug("taskAop.beforeTask work method: ");
		if(!emailSttinggs.isSendNoticeEnabled()){
			return;
		}
		String methodName = jp.getSignature().getName();
		log.debug("taskAop.beforeTask work method: " + methodName);
		Object[] args = jp.getArgs();
		TaskWork baseTask = (TaskWork) jp.getTarget();
		Object obj = getNoticeMap(baseTask.getTaskName(), args != null
				&& args.length > 0, methodName, JsonUtil.getJson(args));
		sendMail(getFreeMarkerBody("template/notice.ftl", obj),
				MailType.NOTICE, baseTask.getTaskName());
	}

	@AfterReturning(value="taskMethod()",returning="returnValue")
	public void afterReturnTask(JoinPoint jp, Object returnValue) {
		if(!emailSttinggs.isSendSuccessEnabled()){
			return;
		}
		String body;
		if (returnValue != null && returnValue instanceof SchedulerLogDomain) {
			body = getFreeMarkerBody("template/success.ftl", returnValue);
		} else {
			body = "None source data.";
		}
		TaskWork baseTask = (TaskWork) jp.getTarget();
		sendMail(body, MailType.SUCCESS, baseTask.getTaskName());
	}

	@AfterThrowing(value = "taskMethod()", throwing = "ex")
	public void processTaskException(JoinPoint jp, Exception ex) {
		TaskWork baseTask = (TaskWork) jp.getTarget();
		if (ex instanceof SchedulerFailureException) {
			SchedulerFailureException e = (SchedulerFailureException) ex;
			sendMail(getFreeMarkerBody("template/failure.ftl", e.getDomains()),
					MailType.ERROR, baseTask.getTaskName());
		} else {
			sendMail(ExceptionUtils.getMessage(ex), MailType.ERROR,
					baseTask.getTaskName());
		}
	}
	@AfterThrowing(value = "rerunMethod()", throwing = "ex")
	public void processRerunException(JoinPoint jp, Exception ex) {
		TaskWork baseTask = (TaskWork) jp.getTarget();
		if (ex instanceof SchedulerFailureException) {
			SchedulerFailureException e = (SchedulerFailureException) ex;
			sendMail(getFreeMarkerBody("template/failure_rerun.ftl", e.getDomains()),
					MailType.ERROR, baseTask.getTaskName());
		} else {
			sendMail(ExceptionUtils.getMessage(ex), MailType.ERROR,
					baseTask.getTaskName());
		}
	}
	void sendMail(String body, MailType mailType, String taskName) {
		BaseMailDto mailDto = new BaseMailDto();
		mailDto.setMailFromAddress(emailSttinggs.getSendFormAddress());
		if (mailType.equals(MailType.NOTICE) && StringUtils.isBlank(body)) {
		}
		mailDto.setMailBody(body);
		mailDto.setMailSubject(SchedulerUtil.getMailSubject(emailSttinggs.getSendSubjectFormat(),
				mailType, taskName));
		if (mailType.equals(MailType.ERROR)) {
			mailDto.setMailToAddress(emailSttinggs.getSendErrorToAddress());
		} else {
			mailDto.setMailToAddress(emailSttinggs.getSendNoticeToAddress());
		}
		try {
			restSupport.put(restfulSendEmailURL,
					mailDto, String.class);
		} catch (Exception e) {
			log.error(ExceptionUtils.getMessage(e));
		}
	}

	static String getFreeMarkerBody(String tmpl, Object object) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("root", object);
		String as = FreemarkConfigFactory.parseTemplate(tmpl, "zh", map);
		return as;
	}

	Map<String, Object> getNoticeMap(String taskName, boolean isManual,
			String methodName, String arguments) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("taskName", taskName);
		map.put("isManual", isManual);
		map.put("methodName", methodName);
		map.put("arguments", arguments);
		return map;
	}
	@Override
	public int getOrder() {
		return 0;
	}

}
