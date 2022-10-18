package com.example.springbackstage.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springbackstage.common.Result;
import com.example.springbackstage.entity.User;
import com.example.springbackstage.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController    //返回json数据
@RequestMapping("/user")  //定义路由
public class UserController {

    @Resource
    UserMapper userMapper;  //我这里直接引入的mapper,并没有引入service

    @PostMapping  //post接口
    public Result<?> save(@RequestBody User user) {  //会将前端传进来的user的json转为User对象
        userMapper.idAdd();   //id自增 最大值加一
        if (user.getPassword() == null) {   //判断密码非空,会自动设置默认密码
            user.setPassword("123123");
        }
        userMapper.insert(user);  //userMapper做增加操作
        return Result.success();  //返回结果
    }

    @GetMapping
    //get请求的查询     pageNum当前页,默认第1页   pageSize每页多少条,默认显示10条1页     search搜索的关键字,默认空,模糊查询
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "") String Search) {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();  //mybatis-plus提供的类
        if (StrUtil.isNotBlank(Search)) {
            wrapper.like(User::getUsername, Search);
        }
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(userPage);
    }

    @PutMapping
    //putMapping是更新
    public Result<?> update(@RequestBody User user) {
        userMapper.updateById(user);
        return Result.success();
    }
    @DeleteMapping("/{id}")  //根据id删除
    public Result<?> delete(@PathVariable Long id) {
        userMapper.deleteById(id);
        return Result.success();
    }
}