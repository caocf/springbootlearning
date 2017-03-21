package com.sos.tpl.modules.sample.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sos.tpl.common.web.mvc.controller.RestClientController;
import com.sos.tpl.common.web.mvc.domain.UserDomain;
import com.sos.tpl.modules.sample.services.SampleAllService;
import com.sos.tpl.modules.sys.services.UserService;

@Controller
public class HomeController extends RestClientController {
	
	private static Log log = LogFactory.getLog(HomeController.class);
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private SampleAllService sampleAllService;
	
	@RequestMapping("/")
	public ModelAndView modifyAuthPage(){
		ModelAndView mav = new ModelAndView("index");
		String hellokey ="world";
		UserDomain user1 = new UserDomain();
		user1.setUserName("测试人员");
		user1.setMobile("13764026448");
		userService.save(user1);
		List<UserDomain> users= userService.findAll();
		if(!users.isEmpty()){
			for(UserDomain user : users){
				hellokey = user.getUserName();
				System.out.println("user UserName:"+user.getUserName());
			}
		}
		mav.addObject("hellokey", hellokey);
		return mav;
	}
	
	@RequestMapping("/sayHello")
	public ModelAndView sayHello(){
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("hellokey", "arenzhj");
		return mav;
	}
}