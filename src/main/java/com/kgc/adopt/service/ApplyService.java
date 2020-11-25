package com.kgc.adopt.service;

import com.github.pagehelper.PageInfo;
import com.kgc.adopt.pojo.Apply;

public interface ApplyService {
    PageInfo<Apply> allApply(Integer state, Integer pageNum, Integer pageSize);
    Apply findByid(Integer id);
    int updateApply(Apply apply);
    int del(Integer id);
    Integer create(Apply apply);
}
