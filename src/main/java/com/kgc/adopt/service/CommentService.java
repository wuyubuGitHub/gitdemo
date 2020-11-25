package com.kgc.adopt.service;

import com.github.pagehelper.PageInfo;
import com.kgc.adopt.pojo.Comment;

import java.util.List;

public interface CommentService {
    PageInfo<Comment> allComment(String userName, Integer pn, Integer pageSize);
    List<Comment> petComments(Integer petId);
    Integer create(Integer userId, Integer petId, String content);
    Comment findById(Integer id);
    PageInfo<Comment> all(String userName, Integer pageNum, Integer pageSize);
    int update(Comment adoptAnimal);
    int delete(Integer id);
}
