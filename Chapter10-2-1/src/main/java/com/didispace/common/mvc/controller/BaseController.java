package com.didispace.common.mvc.controller;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.didispace.common.model.Datagrid;
import com.didispace.common.mvc.dao.orm.Page;
import com.didispace.common.mvc.dao.orm.PropertyFilter;
import com.didispace.common.mvc.dao.orm.hibernate.HibernateWebUtils;
import com.didispace.common.web.springmvc.SpringMVCHolder;

public abstract class BaseController<T, PK extends Serializable> implements ApplicationController{
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
    
    
    
    /**
     * EasyUI 列表数据
     * @return
     */
    @RequestMapping(value = {"datagrid"})
    @ResponseBody
    public Datagrid<T> datagrid() {
        HttpServletRequest request = SpringMVCHolder.getRequest();
        // 自动构造属性过滤器
        List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(request);
        Page<T> p = new Page<T>(request);
//        p = getEntityManager().findPage(p, filters,true);
        Datagrid<T> datagrid = new Datagrid<T>(p.getTotalCount(), p.getResult());
        return datagrid;
    }
    
}