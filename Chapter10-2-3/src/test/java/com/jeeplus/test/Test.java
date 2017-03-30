package com.jeeplus.test;

import com.jeeplus.modules.sys.service.SystemService;

public class Test {

	public static void main(String[] args) {
		/**
		 * 重新生成新密码
		 */
		getEntryptPwd();
	}
	
	/**
	 * 重新生成新密码
	 */
	public static void getEntryptPwd(){
		String newPwd ="123456";
		String enNewPwd = SystemService.entryptPassword(newPwd);
		System.out.println(enNewPwd);
	}
}
