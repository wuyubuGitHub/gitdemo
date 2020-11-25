package com.kgc.adopt.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.adopt.mapper.AdminMapper;
import com.kgc.adopt.pojo.Admins;
import com.kgc.adopt.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminMapper adminMapper;
    @Override
    public PageInfo<Admins> adminPage(String adminName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        EntityWrapper wrapper = new EntityWrapper();
        if(adminName != null && !"".equals(adminName)){
            wrapper.like("adminName",adminName);
        }
        List list = adminMapper.selectList(wrapper);
        PageInfo<Admins> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Admins login(String adminName, String adminPwd) {
        Admins a = new Admins();
        a.setAdminName(adminName);
        Admins admin = adminMapper.selectOne(a);
        if (admin != null && admin.getAdminPwd().equals(adminPwd)){
            return admin;
        }
        return null;
    }

    @Override
    public PageInfo<Admins> allAdmin(String adminName, int pageNum, int pageSize) {
        return null;
    }

    @Override
    public int add(Admins admins) {
        return adminMapper.insert(admins);
    }

    @Override
    public int update(Admins admins) {
        return adminMapper.updateById(admins);
    }

    @Override
    public Admins findById(Integer id) {
        return adminMapper.selectById(id);
    }

    @Override
    public int del(Integer id) {
        return adminMapper.deleteById(id);
    }
}
