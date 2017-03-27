package com.sos.fleet.common.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sos.fleet.common.captcha.SimpleCaptcha;
import com.sos.fleet.common.settings.CaptchaSettings;

@Controller
public class CaptchaController extends BaseController implements ApplicationController{

	@Autowired
	private SimpleCaptcha simpleCaptcha;
	@RequestMapping(value="captcha",produces="image/jpeg")
	public void get(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		simpleCaptcha.writeAndAddToSession(request, response, CaptchaSettings.LOGIN_CAPTCHA);
	}

}
