package com.ls.controller;


import com.alibaba.fastjson.JSONObject;
import com.ls.service.GoodsSv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/swift_life")
@CrossOrigin
public class GoodsController {

    @Autowired
    GoodsSv goodsSv;

    @GetMapping(value = "/goods")
    public JSONObject getGoodsList(@RequestParam("query") String query,@RequestParam("userid") Integer user_id){
        JSONObject jsonObject = goodsSv.getGoodsList(query,user_id);
        return jsonObject;
    }

    @GetMapping(value = "/allgoods")
    public JSONObject getAllGoodsList(@RequestParam("query") String query){
        JSONObject jsonObject = goodsSv.getAllGoodsList(query);
        return jsonObject;
    }

    /**
     * 发布商品
     */
    @PostMapping(value = "/goods")
    public JSONObject setGoods(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("files");
        JSONObject jsonObject = goodsSv.setGoods(params,files);
        return jsonObject;

    }

    /**
     * 删除商品
     */
    @DeleteMapping(value = "/goods/{id}")
    public JSONObject deleteGoods(@PathVariable("id") Integer id){
        JSONObject jsonObject = goodsSv.deleteGoods(id);
        return jsonObject;

    }

    /**
     * 修改商品
     */
    @PostMapping(value = "/updatagoods")
    public JSONObject updataGoods(@RequestBody JSONObject requestJson){
        JSONObject jsonObject = goodsSv.updataGoods(requestJson);
        return jsonObject;

    }
}
