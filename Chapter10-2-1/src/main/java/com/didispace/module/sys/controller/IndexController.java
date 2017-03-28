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
@RequestMapping("${adminPath}/")
public class IndexController extends BaseController{

    static Map<Long, UserDomain> users = Collections.synchronizedMap(new HashMap<Long, UserDomain>());

    @Autowired
	private UserService userService;
	
    
    @RequestMapping(value = {"index/welcome", ""})
    public String welcome() throws Exception {
        return "login";
    }
    
    @RequestMapping(value = {"index"})
    public String index(String theme) {
        //根据客户端指定的参数跳转至 不同的主题 如果未指定 默认:index
        if (StringUtils.isNotBlank(theme) && (theme.equals("app") || theme.equals("index"))) {
            return "layout/" + theme;
        } else {
            return "layout/index";
        }
    }

    @RequestMapping("index/west")
    public String west(ModelMap map){
    	
    	map.addAttribute("user", null);
        String userPhoto = null;
//        if(StringUtils.isNotBlank(sessionUser.getPhoto())){
//            userPhoto = SpringMVCHolder.getRequest().getContextPath()+ sessionUser.getPhoto();
//        }else{
            userPhoto = SpringMVCHolder.getRequest().getContextPath()+"/static/img/icon_boy.png";
//        }
        map.addAttribute("userPhoto", userPhoto);
        return "layout/west";
    }
    
    
    /**
     * 导航菜单.
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     */
    @ResponseBody
    @RequestMapping(value = {"index/navTree"})
    public List<TreeNode> navTree(HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException {
        WebUtils.setNoCacheHeader(response);
        List<TreeNode> treeNodes = Lists.newArrayList();
        String listStr="[{\"id\" : \"1\",   \"pId\" : null,   \"text\" : \"系统管理\",   \"iconCls\" : \"eu-icon-application\",   \"checked\" : false,   \"attributes\" : {     \"markUrl\" : \"\",     \"code\" : \"\",     \"type\" : 0,     \"url\" : \"\"   },   \"children\" : [ {     \"id\" : \"2\",     \"pId\" : null,     \"text\" : \"资源管理\",     \"iconCls\" : \"eu-icon-folder\",     \"checked\" : false,     \"attributes\" : {       \"markUrl\" : \"\",       \"code\" : \"\",       \"type\" : 0,       \"url\" : \"sys/resource\"     },     \"children\" : [ ],     \"state\" : \"open\"   }, {     \"id\" : \"3\",     \"pId\" : null,     \"text\" : \"角色管理\",     \"iconCls\" : \"eu-icon-group\",     \"checked\" : false,     \"attributes\" : {       \"markUrl\" : \"\",       \"code\" : \"\",       \"type\" : 0,       \"url\" : \"sys/role\"     },     \"children\" : [ ],     \"state\" : \"open\"   }, {     \"id\" : \"4\",     \"pId\" : null,     \"text\" : \"机构管理\",     \"iconCls\" : \"eu-icon-group\",     \"checked\" : false,     \"attributes\" : {       \"markUrl\" : \"\",       \"code\" : \"\",       \"type\" : 0,       \"url\" : \"sys/organ\"     },     \"children\" : [ ],     \"state\" : \"open\"   }, {     \"id\" : \"26\",     \"pId\" : null,     \"text\" : \"岗位管理\",     \"iconCls\" : \"eu-icon-vcard\",     \"checked\" : false,     \"attributes\" : {       \"markUrl\" : \"\",       \"code\" : \"\",       \"type\" : 0,       \"url\" : \"sys/post\"     },     \"children\" : [ ],     \"state\" : \"open\"   }, {     \"id\" : \"5\",     \"pId\" : null,     \"text\" : \"用户管理\",     \"iconCls\" : \"eu-icon-user\",     \"checked\" : false,     \"attributes\" : {       \"markUrl\" : \"\",       \"code\" : \"\",       \"type\" : 0,       \"url\" : \"sys/user\"     },     \"children\" : [ ],     \"state\" : \"open\"   }, {     \"id\" : \"7\",     \"pId\" : null,     \"text\" : \"字典类型\",     \"iconCls\" : \"eu-icon-book\",     \"checked\" : false,     \"attributes\" : {       \"markUrl\" : \"\",       \"code\" : \"\",       \"type\" : 0,       \"url\" : \"sys/dictionary-type\"     },     \"children\" : [ ],     \"state\" : \"open\"   }, {     \"id\" : \"8\",     \"pId\" : null,     \"text\" : \"数据字典\",     \"iconCls\" : \"eu-icon-email\",     \"checked\" : false,     \"attributes\" : {       \"markUrl\" : \"\",       \"code\" : \"\",       \"type\" : 0,       \"url\" : \"sys/dictionary\"     },     \"children\" : [ ],     \"state\" : \"open\"   }, {     \"id\" : \"9\",     \"pId\" : null,     \"text\" : \"内容管理\",     \"iconCls\" : \"eu-icon-bug\",     \"checked\" : false,     \"attributes\" : {       \"markUrl\" : \"\",       \"code\" : \"\",       \"type\" : 0,       \"url\" : \"sys/bug\"     },     \"children\" : [ ],     \"state\" : \"open\"   }, {     \"id\" : \"21\",     \"pId\" : null,     \"text\" : \"日志管理\",     \"iconCls\" : \"eu-icon-monitor\",     \"checked\" : false,     \"attributes\" : {       \"markUrl\" : \"\",       \"code\" : \"\",       \"type\" : 0,       \"url\" : \"sys/log\"     },     \"children\" : [ ],     \"state\" : \"open\"   }, {     \"id\" : \"29\",     \"pId\" : null,     \"text\" : \"配置管理\",     \"iconCls\" : \"\",     \"checked\" : false,     \"attributes\" : {       \"markUrl\" : \"\",       \"code\" : \"\",       \"type\" : 0,       \"url\" : \"sys/config\"     },     \"children\" : [ ],     \"state\" : \"open\"   }, {     \"id\" : \"30\",     \"pId\" : null,     \"text\" : \"云盘管理\",     \"iconCls\" : \"\",     \"checked\" : false,     \"attributes\" : {       \"markUrl\" : \"\",       \"code\" : \"\",       \"type\" : 0,       \"url\" : \"disk\"     },     \"children\" : [ ],     \"state\" : \"open\"   }, {     \"id\" : \"33\",     \"pId\" : null,     \"text\" : \"流程设置\",     \"iconCls\" : \"eu-icon-application\",     \"checked\" : false,     \"attributes\" : {       \"markUrl\" : \"\",       \"code\" : \"\",       \"type\" : 0,       \"url\" : \"\"     },     \"children\" : [ {       \"id\" : \"34\",       \"pId\" : null,       \"text\" : \"流程分类\",       \"iconCls\" : \"\",       \"checked\" : false,       \"attributes\" : {         \"markUrl\" : \"\",         \"code\" : \"\",         \"type\" : 0,         \"url\" : \"act/actprocesstype\"       },       \"children\" : [ ],       \"state\" : \"open\"     }, {       \"id\" : \"35\",       \"pId\" : null,       \"text\" : \"流程部署\",       \"iconCls\" : \"\",       \"checked\" : false,       \"attributes\" : {         \"markUrl\" : \"\",         \"code\" : \"\",         \"type\" : 0,         \"url\" : \"act/actprocessdeploy\"       },       \"children\" : [ ],       \"state\" : \"open\"     }, {       \"id\" : \"36\",       \"pId\" : null,       \"text\" : \"流程定义\",       \"iconCls\" : \"\",       \"checked\" : false,       \"attributes\" : {         \"markUrl\" : \"\",         \"code\" : \"\",         \"type\" : 0,         \"url\" : \"act/actprocessdef\"       },       \"children\" : [ ],       \"state\" : \"open\"     }, {       \"id\" : \"37\",       \"pId\" : null,       \"text\" : \"流程表单\",       \"iconCls\" : \"\",       \"checked\" : false,       \"attributes\" : {         \"markUrl\" : \"\",         \"code\" : \"\",         \"type\" : 0,         \"url\" : \"act/actprocessform\"       },       \"children\" : [ ],       \"state\" : \"open\"     } ],     \"state\" : \"open\"   } ],   \"state\" : \"open\" }, {   \"id\" : \"31\",   \"pId\" : null,   \"text\" : \"我的工作\",   \"iconCls\" : \"eu-icon-application\",   \"checked\" : false,   \"attributes\" : {     \"markUrl\" : \"\",     \"code\" : \"\",     \"type\" : 0,     \"url\" : \"\"   },   \"children\" : [ {     \"id\" : \"32\",     \"pId\" : null,     \"text\" : \"我的通知\",     \"iconCls\" : \"\",     \"checked\" : false,     \"attributes\" : {       \"markUrl\" : \"\",       \"code\" : \"\",       \"type\" : 0,       \"url\" : \"notice/notice\"     },     \"children\" : [ ],     \"state\" : \"open\"   } ],   \"state\" : \"open\" } ]";
		treeNodes = JsonMapper.getInstance().readValue(listStr, new TypeReference<List<TreeNode>>() {});
//        SessionInfo sessionInfo = SecurityUtils.getCurrentSessionInfo();
//        if (sessionInfo != null) {
//            treeNodes = resourceManager.getNavMenuTreeByUserId(sessionInfo.getUserId());
//        }
        return treeNodes;
    }

    

}