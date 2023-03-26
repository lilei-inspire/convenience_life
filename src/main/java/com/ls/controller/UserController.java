package com.ls.controller;

import com.alibaba.fastjson.JSONObject;
import com.ls.pojo.user.UserBase;
import com.ls.service.UserManageSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/swift_life")
@CrossOrigin
public class UserController {

    @Autowired
    UserManageSv userManageSv;

    /**
     * 获取单个用户信息
     */
    @GetMapping(value = "/users/{id}")
    public JSONObject getUser(@PathVariable("id") Integer id){
        JSONObject jsonObject = userManageSv.getUser(id);
        return jsonObject;
    }

    /**
     * 修改用户信息
     */
    @PostMapping(value = "/users")
    public JSONObject updataUser(@RequestBody UserBase userBase){
        JSONObject jsonObject = userManageSv.updataUserBase(userBase);
        return jsonObject;
    }

    /**
     * 注销用户
     */
    @DeleteMapping(value = "/users/{id}")
    public JSONObject deleteUser(@PathVariable("id") Integer id){
        JSONObject jsonObject = userManageSv.deleteUserBase(id);
        return jsonObject;
    }

    /**
     * 用户列表
     */
    @GetMapping(value = "/users")
    public JSONObject getUserList(@RequestParam("query") String query){
        JSONObject jsonObject = userManageSv.getUserList(query);
        return jsonObject;
    }

    /**
     * 修改用户角色
     */
    @PutMapping(value = "/users/{id}/role/{role}")
    public JSONObject updataUserRole(@PathVariable("id") Integer id,@PathVariable("role") String role){
        JSONObject jsonObject = userManageSv.updataUserRole(id,role);
        return jsonObject;
    }

    /**
     * 修改用户状态
     */
    @PutMapping(value = "/users/{id}/state/{state}")
    public JSONObject updataUserState(@PathVariable("id") Integer id,@PathVariable("state") String state){
        JSONObject jsonObject = userManageSv.updataUserState(id,state);
        return jsonObject;
    }
}
