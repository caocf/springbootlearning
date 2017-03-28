package com.didispace.module.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.didispace.common.exception.MyException;

@Controller
public class HelloController {
    
    @ResponseBody
    @RequestMapping("/hello")
    public String hello() throws Exception {
        throw new Exception("发生错误");
    }

    @RequestMapping("/h")
    public String index(ModelMap map) {
        map.addAttribute("host", "http://blog.didispace.com");
        return "hello";
    }
    
    @RequestMapping("/json")
    public String json() throws MyException {
        throw new MyException("发生错误2");
    }

    @RequestMapping("/")
    public String index1(ModelMap map) {
        String hellokey ="world111";
        map.addAttribute("hellokey", hellokey);
        return "index";
    }
    
    @RequestMapping("/a")
    public String indexa(ModelMap map) {
        String hellokey ="admin hello";
        map.addAttribute("hellokey", hellokey);
        return "index";
    }
    
    @RequestMapping("/f")
    public String indexf(ModelMap map) {
        String hellokey ="front hello";
        map.addAttribute("hellokey", hellokey);
        return "index";
    }
}