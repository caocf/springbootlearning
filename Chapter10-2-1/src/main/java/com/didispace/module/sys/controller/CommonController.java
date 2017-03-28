package com.didispace.module.sys.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.didispace.common.model.TreeNode;
import com.didispace.common.mvc.controller.BaseController;
import com.didispace.common.utils.StringUtils;
import com.didispace.common.utils.mapper.JsonMapper;
import com.didispace.common.web.springmvc.SpringMVCHolder;
import com.didispace.common.web.utils.WebUtils;
import com.didispace.module.sys.domain.UserDomain;
import com.didispace.module.sys.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.collect.Lists;

/**
 *
 *
 */
@Controller
@RequestMapping("${adminPath}/common")
public class CommonController extends BaseController{

    static Map<Long, UserDomain> users = Collections.synchronizedMap(new HashMap<Long, UserDomain>());

    @Autowired
	private UserService userService;
	

}