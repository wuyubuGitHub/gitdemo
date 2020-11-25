package com.kgc.adopt.service;

import com.github.pagehelper.PageInfo;
import com.kgc.adopt.pojo.AdoptAnimal;

public interface AdoptAnimalService {
    Integer create(AdoptAnimal adoptAnimal);
    PageInfo<AdoptAnimal> all(String adoptTime, Integer pageNum, Integer pageSize);
    int update(Integer id, Integer state);
    PageInfo<AdoptAnimal> allAdoptAnimal(String userName, Integer pageNum, Integer pageSize, Integer state);
}
