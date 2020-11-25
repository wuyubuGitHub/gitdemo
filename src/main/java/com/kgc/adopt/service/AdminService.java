package com.kgc.adopt.service;

import com.github.pagehelper.PageInfo;
import com.kgc.adopt.pojo.Admins;


public interface AdminService {
    PageInfo<Admins> adminPage(String adminName, Integer pageNum, Integer pageSize);
    Admins findById(Integer id);
    Admins login(String adminName, String adminPwd);
    PageInfo<Admins> allAdmin(String adminsName, int pageNum, int pageSize);
    int add(Admins admins);
    int update(Admins admins);
    int del(Integer id);
}
