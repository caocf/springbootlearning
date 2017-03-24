package com.didispace.module.sys.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
//import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.didispace.common.model.Result;
import com.didispace.common.utils.encode.Encrypt;
import com.didispace.module.sys.service.UserService;
import com.didispace.utils.AppConstants;

@Controller
@RequestMapping("${adminPath}/login")
public class LoginController   {
//	protected Logger logger = LoggerFactory.getLogger(getClass());

	private Logger logger = LoggerFactory.getLogger(getClass());//Logger.getLogger(LoginController.class);//LogFactory.getLog(LoginController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/welcome")
    public String welcome(ModelMap map) {
        return "login";
    }
	
	@RequestMapping("/login")
	@ResponseBody
    public Result login(@RequestParam(required = true) String loginName, @RequestParam(required = true) String password,
            String theme, HttpServletRequest request) {
		Result result = null;
        String msg = null;
        // 获取用户信息 admin,admin|21232f297a57a5a743894a0e4a801fc3
        logger.info("用户{}登录系统,password:{},Encrypt password:{}.", loginName, password,Encrypt.e(password));
        String resultUrl = request.getContextPath() +AppConstants.getAdminPath()+ "/index?theme=" + theme;
        
        result = new Result(Result.SUCCESS, "用户验证通过!", resultUrl);
		return result;
    }
	
	
}