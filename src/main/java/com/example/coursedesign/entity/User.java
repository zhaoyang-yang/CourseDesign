package com.example.coursedesign.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;


@Getter
@Setter
@ToString
@TableName("user")
public class User {
    private long id;
    private String name;
    private String password;
    private String company;
    private int type;
    private Date createtime;
    private Date updatetime;
}
