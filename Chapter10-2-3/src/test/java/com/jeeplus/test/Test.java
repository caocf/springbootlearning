package com.jeeplus.test;

import com.jeeplus.common.security.Digests;
import com.jeeplus.common.utils.Encodes;
import com.jeeplus.modules.sys.service.SystemService;

public class Test {
	public static final String HASH_ALGORITHM = "SHA-1";
	public static final int HASH_INTERATIONS = 1024;
	public static final int SALT_SIZE = 8;
	
	public static void main(String[] args) {
		/**
		 * 重新生成新密码
		 */
		getEntryptPwd();
		/**
		 * 验证密码
		 * 0334e21e45ca5dc4fd9b83d7f788f3089033d643aae74c9b9d8974d1
		 */
		getDecodePwd();
	}
	
	/**
	 * 重新生成新密码
	 */
	public static void getEntryptPwd(){
		String newPwd ="123456";
		String enNewPwd = SystemService.entryptPassword(newPwd);
		System.out.println(enNewPwd);
	}
	
	public static void getDecodePwd(){
		String plainPassword ="123456";
		String dbpassword="0334e21e45ca5dc4fd9b83d7f788f3089033d643aae74c9b9d8974d1";
		byte[] salt = Encodes.decodeHex(dbpassword.substring(0,16));
		byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
		String pwsswd = Encodes.encodeHex(salt)+Encodes.encodeHex(hashPassword);
		System.out.println(pwsswd);
	} 
}
