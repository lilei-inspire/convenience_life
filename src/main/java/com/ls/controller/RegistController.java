package com.ls.controller;

import com.alibaba.fastjson.JSONObject;
import com.ls.service.UserRegistSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/swift_life")
@CrossOrigin
public class RegistController {

    @Autowired
    UserRegistSv userRegistSv;

    @PostMapping(value = "/regist")
    public JSONObject userRegist(@RequestBody JSONObject requestJson) throws Exception {
        JSONObject jsonObject = userRegistSv.uerRegist(requestJson);
        return jsonObject;
    }

    @PostMapping(value = "/verify")
    public JSONObject verify(@RequestBody JSONObject requestJson) throws Exception{
        JSONObject jsonObject = userRegistSv.verify(requestJson);
        return jsonObject;
    }

}
