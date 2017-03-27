package com.sos.fleet.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sos.fleet.common.constants.OperationKeys;
import com.sos.fleet.common.constants.OperationTypeKeys;
import com.sos.fleet.common.constants.StatusKeys;
import com.sos.fleet.common.controller.RestClientController;
import com.sos.fleet.common.domain.OperationLogDomain;
import com.sos.fleet.common.exception.ServiceException;
import com.sos.fleet.common.settings.SecuritySettings;
import com.sos.fleet.common.util.OperationUtil;
import com.sos.fleet.common.util.SecurityUtil;
import com.sos.fleet.service.OperationLogService;
import com.sos.fleet.service.UserLdapService;
import com.sos.fleet.service.UserService;

@Controller
@Secured(SecuritySettings.ROLE_USER)
public class UserController extends RestClientController {
	
	private static Log log = LogFactory.getLog(UserController.class);
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserLdapService userLdapServiceImpl;
	@RequestMapping("modifyAuthPage")
	public ModelAndView modifyAuthPage(){
		ModelAndView mav = new ModelAndView("user/modifyPwd");
		OperationUtil.log(OperationKeys.CLICK_MODIFY_AUTHCODE,OperationTypeKeys.MODIFY_AUTHCODE);
		return mav;
	}
	
	@RequestMapping("firstLoginPage")
	public ModelAndView firstLoginPage(){
		ModelAndView mav = new ModelAndView("user/firstLogin");
		OperationUtil.log(OperationKeys.FIRST_LOGIN_PAGE,OperationTypeKeys.FIRST_LOGIN);
		return mav;
	}
	
	@RequestMapping("modifyAuth")
	@ResponseBody
	@PreAuthorize(value=SecuritySettings.DENY_ON_READ_ONLY_ENV)
	public String modifyAuth(@RequestParam String oldPassword, @RequestParam String password){
		String status = "SUCCESS";
		try {
			this.userLdapServiceImpl.modifyPwd(SecurityUtil.getUserName(), oldPassword, password);
		} catch (ServiceException e) {
			// TODO: handle exception
			status = "VALIDATE_FAILED";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			status = "FAILED";
		}
		OperationUtil.log(OperationKeys.MODIFY_AUTHCODE,OperationTypeKeys.MODIFY_AUTHCODE,"SUCCESS".equals(status)?StatusKeys.SUCCESS:StatusKeys.FAILURE);
		return status;
	}
	
	@RequestMapping("firstLogin")
	@ResponseBody
	public String firstLogin(@RequestParam String password){
		String status = "SUCCESS";
		if(StatusKeys.LDAP_FIRST_LOGIN.equals(SecurityUtil.getLdapUser().getFirstLogin())) {
			try {
				this.userLdapServiceImpl.firstLogin(SecurityUtil.getUserName(), password);
				SecurityUtil.getLdapUser().setFirstLogin(StatusKeys.LDAP_NOT_FIRST_LOGIN);
			} catch (ServiceException e) {
				// TODO: handle exception
				e.printStackTrace();
				status = "FAILED";
			}
		}
		OperationUtil.log(OperationKeys.FIRST_LOGIN_RESET_AUTHCODE,OperationTypeKeys.FIRST_LOGIN,"SUCCESS".equals(status)?StatusKeys.SUCCESS:StatusKeys.FAILURE);
		return status;
	}
	
}
