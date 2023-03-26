package com.ls.service;

import com.alibaba.fastjson.JSONObject;

public interface UserRegistSv {

    JSONObject uerRegist(JSONObject jsonObject);

    JSONObject verify(JSONObject jsonObject) throws Exception;
}
