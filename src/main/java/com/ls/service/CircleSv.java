package com.ls.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;


public interface CircleSv {

    JSONObject getDynamicList();

    JSONObject setDynamic(MultipartHttpServletRequest params, List<MultipartFile> files) throws IOException;

    JSONObject setComment(Integer dynamicid,Integer userid,String text);

    JSONObject setLike(Integer dynamicid,Integer userid);

    JSONObject deleteDynamic(Integer dynamicid);

}
