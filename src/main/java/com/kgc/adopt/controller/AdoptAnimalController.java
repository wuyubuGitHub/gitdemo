package com.kgc.adopt.controller;


import com.github.pagehelper.PageInfo;
import com.kgc.adopt.pojo.AdoptAnimal;
import com.kgc.adopt.pojo.Pet;
import com.kgc.adopt.pojo.User;
import com.kgc.adopt.service.AdoptAnimalService;
import com.kgc.adopt.util.Message;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/adopt")
public class AdoptAnimalController {
    @Resource
    private AdoptAnimalService adoptAnimalService;

    @RequestMapping("/apply")
    @ResponseBody
    public Message agree(String userName, @RequestParam("pn") Integer pageNum, Integer state){
        Integer pageSize = 5;
        if(pageNum == 0){
            pageNum = 1;
        }
        PageInfo<AdoptAnimal> pageInfo = adoptAnimalService.allAdoptAnimal(userName, pageNum, pageSize, state);
        return Message.success().add("pageInfo",pageInfo);
    }

    @RequestMapping("/create")
    @ResponseBody
    public Message create(HttpSession session){
        User user = (User) session.getAttribute("user");
        Pet pet = (Pet) session.getAttribute("pet");
        pet.setState(1);
        AdoptAnimal adoptAnimal = new AdoptAnimal();
        adoptAnimal.setUserId(user.getId());
        adoptAnimal.setPetId(pet.getId());
        adoptAnimal.setAdoptTime(new Date());
        adoptAnimal.setState(1);
        adoptAnimalService.create(adoptAnimal);
        return Message.success();
    }

    @RequestMapping("/adopts")
    @ResponseBody
    public Message adopts(@RequestParam(required = false) String adoptTime,@RequestParam(required = false,value = "pn") Integer pageNum){
        int pageSize = 3 ;
        if(pageNum == null){
            pageNum = 1;
        }
        PageInfo<AdoptAnimal> pageInfo = adoptAnimalService.all(adoptTime, pageNum, pageSize);
        return Message.success().add("pageInfo",pageInfo);
    }

    @RequestMapping("/disAgree")
    @ResponseBody
    public Message disAgree(Integer id){
        int update = adoptAnimalService.update(id, 0);
        if(update>0){
           return Message.success();
        }
        return Message.fail();
    }

    @RequestMapping("/agree")
    @ResponseBody
    public Message agree(Integer id){
        System.out.println(id);
        int update = adoptAnimalService.update(id, 2);
        if(update>0){
            return Message.success();
        }
        return Message.fail();
    }
}
