package com.sos.fleet.common.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sos.fleet.common.util.UrlPathUtil;

@RestController
public class KeepAliveController {
	
	public static class KeepAlive{
		private Date date = new Date();
		private String name;
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	
	@RequestMapping("KeepAlive")
	public Object keepAlive(HttpServletRequest request){
		KeepAlive keepAlive =  new KeepAlive();
		String cn = UrlPathUtil.getContextPath(request);
		keepAlive.setName(StringUtils.hasText(cn)&&cn.startsWith("/")?cn.substring(1):cn);
		return keepAlive;
	}
}
