package com.ls.service;

import com.alibaba.fastjson.JSONObject;
import com.ls.pojo.user.UserBase;

import javax.xml.bind.util.JAXBSource;
import java.util.List;

public interface UserManageSv {

    JSONObject getUser(Integer id);

    JSONObject deleteUserBase(Integer id);

    JSONObject updataUserBase(UserBase userBase);

    JSONObject getUserList(String query);

    JSONObject updataUserRole(Integer id,String role);

    JSONObject updataUserState(Integer id,String state);
}
