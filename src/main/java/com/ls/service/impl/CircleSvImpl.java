package com.ls.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ls.dao.CircleDao;
import com.ls.dao.UserBaseDao;
import com.ls.pojo.circle.Comment;
import com.ls.pojo.circle.Dynamic;
import com.ls.pojo.circle.Like;
import com.ls.service.CircleSv;
import com.ls.util.common.CommonUtil;
import com.ls.util.common.DateUtil;
import com.ls.util.common.ImageUtil;
import com.ls.util.constants.ErrorMsg;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CircleSvImpl implements CircleSv {

    public static final transient Log log = LogFactory.getLog(CircleSvImpl.class);

    @Autowired
    CircleDao circleDao;
    @Autowired
    UserBaseDao userBaseDao;

    @Override
    public JSONObject getDynamicList() {
        List<Dynamic> dynamics = circleDao.getDynamicList();
        ImageUtil imageUtil = new ImageUtil();
        DateUtil dateUtil = new DateUtil();
        for (int i = 0; i < dynamics.size(); i++) {

            Integer id = dynamics.get(i).getDynamic_id();
            List<Comment> comments = circleDao.getComments(id);
            List<Like> likes = circleDao.getLikes(id);
            dynamics.get(i).setComments(comments);
            dynamics.get(i).setLikes(likes);

            String imgname = dynamics.get(i).getHead();
            try {
                String bytes = imageUtil.imgToBase("head", imgname);
                dynamics.get(i).setHead(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String imgs = dynamics.get(i).getImgs();
            if (imgs != null) {
                String[] pictures = imgs.split("\\|");
                List<String> picturesbyte = new ArrayList<>();
                for (int j = 0; j < pictures.length; j++) {
                    try {
                        String bytes = imageUtil.imgToBase("picture", pictures[j]);
                        picturesbyte.add(bytes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                dynamics.get(i).setPictures(picturesbyte);
            }
            String timestamp = dynamics.get(i).getTime();
            String msg = dateUtil.CalculateTime(timestamp);
            dynamics.get(i).setTime(msg);
        }
        return CommonUtil.successJson(dynamics);
    }

    @Override
    public JSONObject setDynamic(MultipartHttpServletRequest params, List<MultipartFile> files) throws IOException {
        String text = params.getParameter("textarea");
        String user_id = params.getParameter("user_id");
        MultipartFile file = null;
        ImageUtil imageUtil = new ImageUtil();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            String fileName = file.getOriginalFilename();
            String type = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".") + 1) : null;
            try {
                if (imageUtil.isImg(type)) {
                    String trueFileName = user_id +"--"+ String.valueOf(System.currentTimeMillis()) + "." + type;
                    File fileDir = imageUtil.getFile("circle");
                    File newFile = new File(fileDir.getAbsolutePath() + "/" + trueFileName);
                    file.transferTo(newFile);
                    sb.append(trueFileName).append("|");
                }
            } catch (Exception e) {
                return CommonUtil.errorJson(ErrorMsg.E_904);
            }

        }
        String imgs = sb.deleteCharAt(sb.length() - 1).toString();
        Integer id = Integer.parseInt(user_id);
        JSONObject json = userBaseDao.getUserByID(id);
        Dynamic dynamic = new Dynamic();
        dynamic.setUser_id(id);
        dynamic.setNick_name(json.getString("nick_name"));
        dynamic.setText(text);
        dynamic.setHead(json.getString("head"));
        dynamic.setImgs(imgs);

        try {
            circleDao.insertDynamic(dynamic);
        } catch (Exception e) {
            log.error(e);
            return CommonUtil.errorJson(ErrorMsg.E_905);
        }

        return CommonUtil.successJson();
    }

    @Override
    public JSONObject setComment(Integer dynamicid, Integer userid, String text) {
        JSONObject json = userBaseDao.getUserByID(userid);
        Comment comment = new Comment();
        comment.setId(dynamicid);
        comment.setReview(json.getString("nick_name"));
        comment.setContent(text);
        try {
            circleDao.insertComment(comment);
        } catch (Exception e) {
            log.error(e);
            return CommonUtil.errorJson(ErrorMsg.E_400);
        }
        return CommonUtil.successJson(comment);
    }

    @Override
    public JSONObject setLike(Integer dynamicid, Integer userid) {
        JSONObject json = userBaseDao.getUserByID(userid);
        Like like = new Like();
        like.setDynamic_id(dynamicid);
        like.setLiker_id(userid);
        like.setLiker_name(json.getString("nick_name"));
        try {
            circleDao.insertLike(like);
        } catch (Exception e) {
            return CommonUtil.errorJson(ErrorMsg.E_400);
        }
        return CommonUtil.successJson(like);
    }

    @Override
    public JSONObject deleteDynamic(Integer dynamicid) {
        try{
            circleDao.deleteDynamic(dynamicid);
            circleDao.deleteComment(dynamicid);
            circleDao.deleteLike(dynamicid);
        }catch (Exception e){
            return CommonUtil.errorJson(ErrorMsg.E_400);
        }
        return CommonUtil.successJson();
    }


}
