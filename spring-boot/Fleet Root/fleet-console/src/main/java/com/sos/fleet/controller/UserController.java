package com.sos.fleet.controller;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sos.fleet.common.condition.UserCondition;
import com.sos.fleet.common.constants.OperationKeys;
import com.sos.fleet.common.constants.OperationTypeKeys;
import com.sos.fleet.common.constants.StatusKeys;
import com.sos.fleet.common.controller.RestClientController;
import com.sos.fleet.common.domain.SearchPageImpl;
import com.sos.fleet.common.domain.SearchPageImpl.SimpleOrder;
import com.sos.fleet.common.domain.UserDomain;
import com.sos.fleet.common.domain.UserDomain_;
import com.sos.fleet.common.settings.SecuritySettings;
import com.sos.fleet.common.util.OperationUtil;
import com.sos.fleet.service.UserLdapService;
import com.sos.fleet.service.UserService;

@Controller
@RequestMapping("fu")
@Secured(SecuritySettings.ROLE_ADMIN)
public class UserController extends RestClientController{
	
	@Controller
	@Secured(SecuritySettings.ROLE_ADMIN)
	public static class IndexController {
		@Autowired
		UserController userController;
		@RequestMapping({"/"})
		public ModelAndView getAccountPage(SearchPageImpl<UserDomain> page, UserCondition condition) {
			return userController.getAccountPage(page, condition);
		}
	}
	static Log log = LogFactory.getLog(UserController.class);
	
	@Autowired
	private UserService userServiceImpl;
	
	@Autowired
	private UserLdapService userLdapServiceImpl;

	@RequestMapping({"showUsers","/"})
	public ModelAndView getAccountPage(SearchPageImpl<UserDomain> page, UserCondition condition) {
		log.info("showUsers :::::::::::::::");
		
		ModelAndView view = new ModelAndView("user/accounts");
		page.setSize(8);
		page.setCondition(condition);
		
		SimpleOrder order = null;
		
		if(CollectionUtils.isEmpty(page.getOrders())) {
			page.setOrders(new ArrayList<SimpleOrder>());
			order = new SimpleOrder();
			order.setProperty(UserDomain_.createDate.getName());
			order.setDirection(Direction.DESC);
			page.getOrders().add(order);
			
			order = new SimpleOrder();
			order.setProperty(UserDomain_.id.getName());
			order.setDirection(Direction.DESC);
			page.getOrders().add(order);
		}else {
			order = new SimpleOrder();
			order.setProperty(UserDomain_.id.getName());
			order.setDirection(Direction.DESC);
			page.getOrders().add(order);
		}
		
		page = this.userServiceImpl.findAll(page);
		
		view.addObject("page", page);
		view.addObject("condition", condition);
		OperationUtil.log(OperationKeys.SEARCH_USERS,OperationTypeKeys.USERS_MGMT);
		return view;
	}
	
	@RequestMapping(value="hasUser", method=RequestMethod.POST)
	@ResponseBody
	public Boolean hasUser(@RequestParam String userName) {
//		OperationUtil.log(OperationKeys.VALIDATE_USER_EXISTS,OperationTypeKeys.USERS_MGMT);
		OperationUtil.log(null, OperationKeys.VALIDATE_USER_EXISTS, userName, OperationTypeKeys.USERS_MGMT, null);
		return this.userServiceImpl.hasUser(userName);
	}
	
	@RequestMapping("toAddUser")
	public ModelAndView toAddUser() {
		log.info("to Add User :::::::::::::::");
		ModelAndView view = new ModelAndView("user/user");
		OperationUtil.log(OperationKeys.CLICK_ADD_USER,OperationTypeKeys.USERS_MGMT);
		return view;
	}
	
	@RequestMapping("addUser")
	@ResponseBody
	@PreAuthorize(value=SecuritySettings.DENY_ON_READ_ONLY_ENV)
	public UserDomain addUser(UserDomain user) {
		boolean failure = false;
		log.info("add User :::::::::::::::");
		String mes = null;
		try{
			this.userServiceImpl.saveFetch(user);
		}catch(Exception e){
			mes = e.getMessage();
			log.error(e.getMessage());
			failure = !failure;
		}
		OperationUtil.log(user==null?(Long)null:user.getId(), OperationKeys.ADD_USER, null,failure?StatusKeys.FAILURE:StatusKeys.SUCCESS, OperationTypeKeys.USERS_MGMT, mes);
		log.info("test--------");
		return user;
	}
	
	@RequestMapping("toUpdateUser")
	public ModelAndView toUpdateUser(@RequestParam Long userId) {
		log.info("update User :::::::::::::::");
		ModelAndView view = new ModelAndView("user/user");
		boolean failure = false;
		String mes = null;
		UserDomain user= null;
		try{
			user = this.userServiceImpl.get(userId);
			view.addObject("user", user);
		}catch(Exception e){
			mes = e.getMessage();
			log.error(e.getMessage());
			failure = !failure;
		}
		OperationUtil.log(userId, OperationKeys.CLICK_UPDATE_USER, null,failure?StatusKeys.FAILURE:StatusKeys.SUCCESS, OperationTypeKeys.USERS_MGMT, mes);
		return view;
	}
	
	@RequestMapping("updateUser")
	@ResponseBody
	@PreAuthorize(value=SecuritySettings.DENY_ON_READ_ONLY_ENV)
	public UserDomain updateUser(UserDomain user) throws Exception {
		log.info("update User :::::::::::::::");
		boolean failure = false;
		String mes = null;
		try{
			this.userServiceImpl.updateFetch(user);
		}catch(Exception e){
			mes = e.getMessage();
			log.error(e.getMessage());
			failure = !failure;
		}
		OperationUtil.log(user==null?(Long)null:user.getId(), OperationKeys.UPDATE_USER, null,failure?StatusKeys.FAILURE:StatusKeys.SUCCESS, OperationTypeKeys.USERS_MGMT, mes);
		return user;
	}
	
	@RequestMapping("resetPwd")
	@ResponseBody
	@PreAuthorize(value=SecuritySettings.DENY_ON_READ_ONLY_ENV)
	public Boolean resetPwd(@RequestParam String userName) {
		log.info("resetPassword :::::::::::::::");
		Boolean flag = true;
		String mes = null;
		try {
			this.userLdapServiceImpl.resetPwd(userName);
		} catch (Exception e) {
			mes = e.getMessage();
			log.info("resetPwd fail: " +mes);
			flag = false;
		}
		OperationUtil.log((Long)null, OperationKeys.RESET_PWD, userName,!flag?StatusKeys.FAILURE:StatusKeys.SUCCESS, OperationTypeKeys.USERS_MGMT, mes);
		return flag;
	}
	
}
