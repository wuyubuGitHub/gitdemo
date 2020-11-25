package com.kgc.adopt.controller;

import com.github.pagehelper.PageInfo;
import com.kgc.adopt.pojo.User;
import com.kgc.adopt.service.UserService;
import com.kgc.adopt.util.Message;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/create")
    @ResponseBody
    public Message add(User user){
        int add = userService.add(user);
        if(add>0){
            return Message.success();
        }else{
            return Message.fail();
        }
    }

    @RequestMapping("/login")
    @ResponseBody
    public Message loginuser(HttpSession session, String userName, String password){
        User user = userService.loginuser(userName, password);
        if (user != null){
            session.setAttribute("user",user);
            return Message.success().add("user",user);
        }else{
            return Message.fail();
        }
    }
    @RequestMapping("/logout")
    @ResponseBody
    public Message logout(HttpSession session){
        session.invalidate();
        return Message.success();
    }

    @RequestMapping("/users")
    @ResponseBody
    public Message users(@RequestParam(required = false) String userName, @RequestParam("pn")Integer pageNum){
        Integer pageSize = 4;
        PageInfo<User> users = userService.allUser(userName,pageNum,pageSize);
        return Message.success().add("pageInfo",users);
    }

    @RequestMapping("findById")
    @ResponseBody
    public Message findById(Integer id){
        User byId = userService.findById(id);
        return Message.success().add("user",byId);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Message del(Integer id){
        int del = userService.del(id);
        if(del>0){
            return Message.success();
        }else {
            return Message.fail();
        }
    }

    @RequestMapping("/about")
    public String about(){
        return "user/about";
    }

    @RequestMapping("/error")
    public String error(){
        return "user/error";
    }

    @RequestMapping("/index")
    public String index(){
        return "user/index";
    }

    @RequestMapping("/personal-info")
    public String personalInfo(){
        return "user/personal-info";
    }

    @RequestMapping("/service")
    public String service(){
        return "user/service";
    }

    @RequestMapping("/show")
    public String show(){
        return "user/show";
    }

    @RequestMapping("/team")
    public String team(){
        return "user/team";
    }

    @RequestMapping("/teamBlog")
    public String teamBlog(){
        return "user/teamBlog";
    }

    @RequestMapping("/update")
    @ResponseBody
    public Message update(User user){
        Integer update = userService.update(user);
        if(update > 0){
            return Message.success();
        }else {
            return Message.fail();
        }
    }

    @RequestMapping("/updatePic")
    @ResponseBody
    public Message updatePic(HttpSession session, MultipartFile file){
        //获取上传地址
        String realPath = session.getServletContext().getRealPath("static/images/user/");
        //获取源文件
        String ordName = file.getOriginalFilename();
        //获取扩展名
        String extension = FilenameUtils.getExtension(ordName);
        //生成新文件名
        String newName = System.currentTimeMillis()+ RandomUtils.nextInt(1000,1000000)+"id_pic."+extension;

        //封装文件对象
        File newfile = new File(realPath,newName);
        User user = (User) session.getAttribute("user");
        user.setPic(newName);
        Integer update = userService.update(user);
        if (update > 0){
            //上传文件
            try {
                file.transferTo(newfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Message.success();
        }else {
            return Message.fail();
        }

//        String fileName = FileLoad.uploadUserPic(file);
//        User user = (User) session.getAttribute("user");
//        user.setPic(fileName);
//        Integer update = userService.update(user);
//        if (update > 0){
//            return Message.success();
//        }else {
//            return Message.fail();
//        }
    }
}
