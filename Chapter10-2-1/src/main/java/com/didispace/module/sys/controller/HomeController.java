package com.didispace.module.sys.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didispace.module.sys.service.UserService;

@Controller
public class HomeController   {
	
	private static Log log = LogFactory.getLog(HomeController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/home")
    public String index(ModelMap map) {
		String hellokey ="world11";
        map.addAttribute("hellokey", hellokey);
        return "index";
    }

//	@RequestMapping("/")
//	public String index(){
//		ModelAndView mav = new ModelAndView("index");
//		String hellokey ="world";
//		mav.addObject("hellokey", hellokey);
//		return mav;
//		String hellokey ="world";
//		return "index";
//	}
//	public ModelAndView index(){
//		ModelAndView mav = new ModelAndView("index");
//		String hellokey ="world";
//		mav.addObject("hellokey", hellokey);
//		return mav;
//	}
	
//	@RequestMapping("/sayHello")
//	public ModelAndView sayHello(){
//		ModelAndView mav = new ModelAndView("index");
//		mav.addObject("hellokey", "arenzhj");
//		return mav;
//	}
}