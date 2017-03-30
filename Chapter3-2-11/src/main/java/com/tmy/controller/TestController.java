package com.tmy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tmy.entry.Article;
import com.tmy.entry.Blog;
import com.tmy.repository.base.BaseRepository;

@RestController
public class TestController {
    
    @Autowired
    private List<BaseRepository> repositories;
    
    @RequestMapping(value = "/test", method=RequestMethod.GET)
    public Object getEntry(@RequestParam(value="type", required = true) String type,
            @RequestParam(value="id", required=true) Integer id){
        if(type.equals("article")){
            type = Article.class.getName();
        }else if (type.equals("blog")) {
            type = Blog.class.getName();
        }
        for (BaseRepository baseRepository : repositories) {
            if(baseRepository.support(type)){
                return baseRepository.findOne(id);
            }
        }
        return null;
    }

}
