package com.kgc.adopt.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data
public class AdoptAnimal {
    private Integer id;
    private Integer userId;
    private Integer petId;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date adoptTime;
    private Integer state;

    @TableField(exist = false)
    private Pet pet;
    @TableField(exist = false)
    private User user;

}
