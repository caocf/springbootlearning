package com.sos.fleet.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sos.fleet.common.constants.OperationKeys;
import com.sos.fleet.common.constants.OperationTypeKeys;
import com.sos.fleet.common.constants.SessionKeys;
import com.sos.fleet.common.rest.RestClient;
import com.sos.fleet.common.service.SubNotificationService;
import com.sos.fleet.common.settings.EnvironmentSettings;
import com.sos.fleet.common.settings.NotificationSetting;
import com.sos.fleet.common.settings.WebServiceURLSettings;
import com.sos.fleet.common.util.DynamicCodeUtil;
import com.sos.fleet.common.util.OperationUtil;
import com.sos.fleet.common.util.SessionUtils;
import com.sos.fleet.common.util.bean.ResultHolder;

@Controller
public class NotificationController extends BaseController{
	
	
	Log log = LogFactory.getLog(NotificationController.class);

	@Autowired
	private RestClient restClient;
	
	@Autowired
	private SubNotificationService subNotificationService;
	
	@RequestMapping(value = "sendSecurityCode")
	@ResponseBody
	public ResultHolder<String> sendSMS(HttpServletRequest request, @RequestParam String username) {
		
		ResultHolder<String> result = new ResultHolder<String>();
		
		String phone = subNotificationService.getMobileByUserName(username);
		
		String url = WebServiceURLSettings.getWSUrl(WebServiceURLSettings
				.instance().getSendSMS());
		
		String dynamicCode = DynamicCodeUtil.getDynamicCode();
		
		SessionUtils.setAttribute(request, SessionKeys.VALIDATE_CODE_USERNAME, username);
		SessionUtils.setAttribute(request, username+SessionKeys.DYNAMIC_CODE, dynamicCode);
		
		String sendCode = SessionUtils.getAttribute(request, username+SessionKeys.DYNAMIC_CODE, String.class);
//		System.out.println(sendCode+"-==");
		
		if(EnvironmentSettings.IS_PROD_OR_EFO) {
			String msg = NotificationSetting.instance().getTemplete().replace("{0}", dynamicCode);
			Boolean success = restClient.postForObject(url, null, Boolean.class, phone, msg);
			
			result.setSuccess(success);
			log.info("sendSMS : " + success);
		}else {
			result.setSuccess(true);
			result.setData(dynamicCode);
		}
		
		OperationUtil.log(null, OperationKeys.SEND_SMS_CODE, username, OperationTypeKeys.USERS_MGMT, null);
		return result;
	}
}
