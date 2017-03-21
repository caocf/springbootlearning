package com.didispace.module.sys.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didispace.module.sys.service.UserService;

@Controller
@RequestMapping("${adminPath}/login")
public class LoginController   {
	
	private static Log log = LogFactory.getLog(LoginController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/welcome")
    public String login(ModelMap map) {
        return "login";
    }
	
	
}