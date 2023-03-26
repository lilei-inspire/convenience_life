package com.ls.service;


import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

public interface UserLoginSv {

    /**
     * 用户登录
     * */
    JSONObject userLogin(JSONObject jsonObject);

    /**
     * 获取菜单
     * */
    JSONObject getMenu(Integer id);

    /**
     * 获取头像
     * */
    JSONObject getHead(JSONObject jsonObject) throws IOException;

}
