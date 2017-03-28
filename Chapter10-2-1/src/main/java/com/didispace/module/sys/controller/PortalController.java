package com.didispace.module.sys.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.didispace.common.model.Result;
import com.didispace.common.mvc.controller.BaseController;
import com.didispace.common.web.utils.WebUtils;
import com.didispace.module.sys.domain.UserDomain;
import com.didispace.module.sys.service.UserService;
import com.google.common.collect.Maps;

/**
 * Portal主页门户管理
 *
 */
@Controller
@RequestMapping(value = "${adminPath}/portal")
public class PortalController extends BaseController{

    static Map<Long, UserDomain> users = Collections.synchronizedMap(new HashMap<Long, UserDomain>());

    @Autowired
	private UserService userService;
	
    
    @RequestMapping("")
    public String portal(ModelMap map){
        return "layout/portal";
    }


    /**
     * 我的通知
     *
     * @return
     */
    @RequestMapping("notice")
    public String notice(ModelMap map){
        map.addAttribute("noticeScopes", null);
        return "layout/portal-notice";
    }

    /**
     * 个人消息中心
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("mymessages")
    @ResponseBody
    public Result mymessages(HttpServletResponse response) throws Exception {
        WebUtils.setNoCacheHeader(response);
        Result result = null;
        Map<String, Long> map = Maps.newHashMap();
        // 当前登录用户
//        SessionInfo sessionInfo = SecurityUtils.getCurrentSessionInfo();
//        long noticeScopes = noticeScopeManager.getUserUnreadNoticeNum(sessionInfo.getUserId());
        map.put("noticeScopes", null);

        result = Result.successResult().setObj(map);
        return result;
    }

}