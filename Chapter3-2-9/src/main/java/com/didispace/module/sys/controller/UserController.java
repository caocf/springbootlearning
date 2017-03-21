package com.didispace.module.sys.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.didispace.module.sys.domain.UserDomain;
import com.didispace.module.sys.service.UserService;

/**
 *
 * @author 程序猿DD
 * @version 1.0.0
 * @blog http://blog.didispace.com
 *
 */
@RestController
@RequestMapping(value="/users")     // 通过这里配置使下面的映射都在/users下，可去除
public class UserController {

    static Map<Long, UserDomain> users = Collections.synchronizedMap(new HashMap<Long, UserDomain>());

    @Autowired
	private UserService userService;
	
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public List<UserDomain> getUserList() {
        // 处理"/users/"的GET请求，用来获取用户列表
    	List<UserDomain> r = userService.getUsers();
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
//        List<UserDomain> r = new ArrayList<UserDomain>(users.values());
        return r;
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String postUser(@ModelAttribute UserDomain user) {
        // 处理"/users/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        users.put(user.getId(), user);
        return "success";
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public UserDomain getUser(@PathVariable Long id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return users.get(id);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ModelAttribute UserDomain user) {
        // 处理"/users/{id}"的PUT请求，用来更新User信息
    	UserDomain u = users.get(id);
        u.setUserName(user.getUserName());
//        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        // 处理"/users/{id}"的DELETE请求，用来删除User
        users.remove(id);
        return "success";
    }

}