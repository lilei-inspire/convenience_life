package com.ls.controller;

import com.alibaba.fastjson.JSONObject;
import com.ls.service.UserLoginSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/swift_life")
@CrossOrigin
public class LoginController {

    @Autowired
    UserLoginSv userLoginSv;


    @PostMapping(value = "/login")
    public JSONObject userLogin(@RequestBody JSONObject requestJson) {
        JSONObject jsonObject = userLoginSv.userLogin(requestJson);
        return jsonObject;
    }

    @GetMapping(value = "/menus/{id}")
    public JSONObject getMenu(@PathVariable("id") Integer id){
        JSONObject jsonObject = userLoginSv.getMenu(id);
        return jsonObject;
    }

    @PostMapping(value = "/head")
    public JSONObject getHead(@RequestBody JSONObject requestJson) throws IOException {
        JSONObject jsonObject = userLoginSv.getHead(requestJson);
        return jsonObject;
    }
}
