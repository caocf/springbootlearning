package com.didispace.module.sys.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.didispace.common.model.Datagrid;
import com.didispace.common.mvc.controller.BaseController;
import com.didispace.common.mvc.dao.orm.Page;
import com.didispace.common.mvc.dao.orm.PropertyFilter;
import com.didispace.common.mvc.dao.orm.hibernate.HibernateWebUtils;
import com.didispace.common.web.springmvc.SpringMVCHolder;
import com.didispace.module.sys.domain.RoleDomain;
import com.didispace.module.sys.domain.UserDomain;
import com.didispace.module.sys.service.RoleService;

/**
 *
 */
@Controller
@RequestMapping("${adminPath}/sys/role") 
public class RoleController extends BaseController{

    static Map<Long, UserDomain> users = Collections.synchronizedMap(new HashMap<Long, UserDomain>());

//    @Autowired
//	private UserService userService;
    @Autowired
  	private RoleService roleService;
  	
    
    @RequestMapping(value = {""})
    public String list() {
        return "modules/sys/role";
    }
    
    public Datagrid<RoleDomain> datagrid() {
        Page<RoleDomain> page = new Page<RoleDomain>(SpringMVCHolder.getRequest());
        // 自动构造属性过滤器
        List<PropertyFilter> filters = HibernateWebUtils.buildPropertyFilters(SpringMVCHolder.getRequest(), HibernateWebUtils.FILTERPREFIX, false);
//        page = getEntityManager().findPage(page,filters);
        page = roleService.findPage(page, filters, false);
        Datagrid<RoleDomain> datagrid = new Datagrid<RoleDomain>(page.getTotalCount(), page.getResult());
        return datagrid;
    }
    

}