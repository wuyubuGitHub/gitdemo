package com.kgc.adopt.service;

import com.github.pagehelper.PageInfo;
import com.kgc.adopt.pojo.Pet;

public interface PetService {
    PageInfo<Pet> pets(Integer pageNum, Integer pagesize);
    Pet findById(Integer id);
    Integer create(Pet pet);
    PageInfo<Pet> allPet(String petType, Integer pageNum, Integer pageSize);
    int add(Pet pet);
    int update(Pet pet);
    int del(Integer id);
}
