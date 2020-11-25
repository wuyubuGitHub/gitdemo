package com.kgc.adopt.service.impl;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kgc.adopt.mapper.BlogMapper;
import com.kgc.adopt.pojo.Blog;
import com.kgc.adopt.service.BlogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {
    @Resource
    private BlogMapper blogMapper;

    @Override
    public PageInfo<Blog> findAllBlog(String actionTime, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        EntityWrapper wrapper = new EntityWrapper();
        if(actionTime != null && !"".equals(actionTime)){
            wrapper.eq("actionTime",actionTime);
        }
        List list = blogMapper.selectList(wrapper);
        PageInfo<Blog> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int add(Blog blog) {
        return blogMapper.insert(blog);
    }

    @Override
    public Blog findById(Integer id) {
        return blogMapper.selectById(id);
    }

    @Override
    public int updateBlog(Blog blog) {
        return blogMapper.updateById(blog);
    }

    @Override
    public int del(Integer id) {
        return blogMapper.deleteById(id);
    }

    @Override
    public PageInfo<Blog> blogs(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Blog> blogs = blogMapper.selectList(null);
        PageInfo<Blog> pageInfo = new PageInfo<Blog>(blogs, 3);
        return pageInfo;
    }
}
