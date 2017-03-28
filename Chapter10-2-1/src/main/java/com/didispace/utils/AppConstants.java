package com.didispace.utils;

import org.springframework.beans.factory.annotation.Autowired;

import com.didispace.common.settings.AppConstantsSettings;
import com.didispace.common.utils.io.PropertiesLoader;


public class AppConstants {
	
	@Autowired
	private AppConstantsSettings appConstantsSettings;

    private static PropertiesLoader sqlfilter = null;
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
	
	/**
     * SQL参数过滤配置文件(sqlfilter.properties)
     */
    public static PropertiesLoader getSqlfilter() {
    	if(sqlfilter == null){
    		sqlfilter = new PropertiesLoader("sqlfilter.properties");
    	}
        return sqlfilter;
    }
}
