package com.example.springbackstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.springbackstage.entity.User;
import org.apache.ibatis.annotations.Update;

public interface UserMapper extends BaseMapper<User> {
    //mysql做数据删除操作之后,id自增会乱序,添加这一行的作用是,自增将会从id最大值加一
    @Update("alter table user auto_increment = 1")

    public void idAdd();
}