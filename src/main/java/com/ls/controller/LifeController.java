package com.ls.controller;

import com.alibaba.fastjson.JSONObject;
import com.ls.service.CircleSv;
import com.ls.util.common.CommonUtil;
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
public class LifeController {

    @Autowired
    CircleSv circleSv;

    /**
     * 获取文章列表
     */
    @GetMapping(value = "/article")
    public JSONObject getArticleList() {
        return CommonUtil.successJson();
    }

    /**
     * 获取动态列表
     */
    @GetMapping(value = "/dynamics")
    public JSONObject getDynamicList() {
        JSONObject jsonObject = circleSv.getDynamicList();
        return jsonObject;
    }

    /**
     * 发布动态
     */
    @PostMapping(value = "/upload")
    public JSONObject setDynamic(HttpServletRequest request) throws IOException {
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("files");
        JSONObject jsonObject = circleSv.setDynamic(params,files);
        return jsonObject;

    }

    /**
     * 点赞
     */
    @PostMapping(value = "/like")
    public JSONObject setLike(@RequestBody JSONObject requestJson){
        Integer dynamicid = requestJson.getInteger("dynamicid");
        Integer userid = requestJson.getInteger("userid");
        JSONObject jsonObject = circleSv.setLike(dynamicid,userid);
        return jsonObject;
    }

    /**
     * 评论
     */
    @PostMapping(value = "/comment")
    public JSONObject setComment(@RequestBody JSONObject requestJson){
        Integer dynamicid = requestJson.getInteger("dynamicid");
        Integer userid = requestJson.getInteger("userid");
        String comment = requestJson.getString("comment");
        JSONObject jsonObject = circleSv.setComment(dynamicid,userid,comment);
        return jsonObject;
    }

    /**
     * 删除动态
     */
    @DeleteMapping(value = "/deldynamic")
    public JSONObject deleteDynamic(@RequestBody JSONObject requestJson){
        Integer dynamicid = requestJson.getInteger("dynamicid");
        JSONObject jsonObject = circleSv.deleteDynamic(dynamicid);
        return jsonObject;
    }
}

