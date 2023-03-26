package com.ls.dao;

import com.alibaba.fastjson.JSONObject;
import com.ls.pojo.user.UserBase;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserBaseDao {

    JSONObject getUserByUserName(@Param("username") String username);

    JSONObject getUserByID(Integer id);

    List<UserBase> getUserBaseList(String name);

    void insertUserBase(UserBase userBase);

    void deleteUserBase(Integer id);

    void updateUserBase(@Param("user") UserBase userBase, @Param("id") Integer id);

}
