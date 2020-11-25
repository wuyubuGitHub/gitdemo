package com.kgc.adopt.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kgc.adopt.pojo.AdoptAnimal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdoptAnimalMapper extends BaseMapper<AdoptAnimal> {
        List<AdoptAnimal> all(@Param("adoptTime") String adoptTime);

}
