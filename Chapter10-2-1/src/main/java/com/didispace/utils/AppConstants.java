package com.didispace.utils;

import org.springframework.beans.factory.annotation.Autowired;

import com.didispace.common.settings.AppConstantsSettings;


public class AppConstants {
	
	@Autowired
	private AppConstantsSettings appConstantsSettings;
	
	  /**
     * 系统初始化时间
     */
    public static long SYS_INIT_TIME = System.currentTimeMillis();

	public static String getAdminPath(){
		return AppConstantsSettings.instance().getAdminPath();
	}
	
	public static String getFrontPath(){
		return AppConstantsSettings.instance().getFrontPath();
	}
}
