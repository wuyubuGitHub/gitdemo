package com.kgc.adopt.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.adopt.pojo.Blog;
import com.kgc.adopt.service.BlogService;
import com.kgc.adopt.util.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Resource
    private BlogService blogService;
    @RequestMapping("/blogs")
    @ResponseBody
    public Message blogs(String actionTime, @RequestParam("pn") Integer pageNum){
        if(pageNum == null){
            pageNum = 1;
        }
        System.out.println(actionTime);
        PageInfo<Blog> allBlog = blogService.findAllBlog(actionTime, pageNum, 3);
        return Message.success().add("pageInfo",allBlog);
    }
    @RequestMapping("/create")
    @ResponseBody
    public Message creat(Blog blog){
        int i = blogService.add(blog);
        if(i > 0){
            return Message.success();
        }else {
            return Message.fail();
        }
    }
    @RequestMapping("/update")
    @ResponseBody
    public Message update(Blog blog){
        int i = blogService.updateBlog(blog);
        if(i > 0){
            return Message.success();
        }else {
            return Message.fail();
        }
    }
    @RequestMapping("/delete")
    @ResponseBody
    public Message delete(Integer id){
        int i = blogService.del(id);
        if(i > 0){
            return Message.success();
        }else {
            return Message.fail();
        }
    }

    @RequestMapping("/findById")
    @ResponseBody
    public Message findById(Integer id){
        Blog blog = blogService.findById(id);
        return Message.success().add("blog", blog);
    }
}
