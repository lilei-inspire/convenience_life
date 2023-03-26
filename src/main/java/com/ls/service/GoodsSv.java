package com.ls.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

public interface GoodsSv {

    JSONObject getGoodsList(String query,Integer id);

    JSONObject getAllGoodsList(String query);

    JSONObject setGoods(MultipartHttpServletRequest params, List<MultipartFile> files) throws IOException;

    JSONObject deleteGoods(Integer id);

    JSONObject updataGoods(JSONObject requestJson);


}
