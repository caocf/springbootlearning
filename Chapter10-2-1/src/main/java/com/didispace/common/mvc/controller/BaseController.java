package com.didispace.common.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public abstract class BaseController implements ApplicationController{
	/**
     * 日志对象
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

	 
	 /**
	     * 用户跳转JSP页面
	     * <p/>
	     * 此方法不考虑权限控制
	     *
	     * @param folder 路径
	     * @param pageName 页面名称(不加后缀)
	     * @return 指定JSP页面
	     */
    @RequestMapping("/{folder}/{pageName}")
    public String redirectJsp(@PathVariable String folder, @PathVariable String pageName) {
        return "/" + folder + "/" + pageName;
    }
}