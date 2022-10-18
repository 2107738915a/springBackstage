package com.example.springbackstage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("user")  //数据库的表名
@Data   //lombook注解简化get,set方法
public class User {
    @TableId(type = IdType.AUTO)   //用于id自增
    private Integer id;      //user表的id
    private String username;  //user表的username
    private String password;  //password
    private String nickName;  //昵称
    private int age;   //年龄
    private String gender;  //性别
    private String address;  //地址
}

