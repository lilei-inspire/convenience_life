package com.ls.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ls.dao.GoodsDao;
import com.ls.dao.UserBaseDao;
import com.ls.pojo.goods.Goods;
import com.ls.service.GoodsSv;
import com.ls.util.common.CommonUtil;
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
public class GoodsSvImpl implements GoodsSv {

    public static final transient Log log = LogFactory.getLog(GoodsSvImpl.class);

    @Autowired
    GoodsDao goodsDao;
    @Autowired
    UserBaseDao userBaseDao;

    @Override
    public JSONObject getGoodsList(String query,Integer id) {
        List<Goods> goods = goodsDao.getGoodsListByUserID(query,id);
        Integer total = goods.size();
        return CommonUtil.successJson(total,goods);
    }

    @Override
    public JSONObject getAllGoodsList(String query) {
        List<Goods> goods = goodsDao.getGoodsList(query);
        Integer total = goods.size();
        ImageUtil imageUtil = new ImageUtil();
        JSONObject jsonObject = null;
        for (int i = 0; i < total; i++) {
            Integer id = goods.get(i).getUser_id();
            jsonObject = userBaseDao.getUserByID(id);
            goods.get(i).setGoods_seller(jsonObject.getString("nick_name"));

            String goodsImg = goods.get(i).getGoods_pic();
            if (goodsImg != null) {
                String[] pictures = goodsImg.split("\\|");
                List<String> picturesbyte = new ArrayList<>();
                for (int j = 0; j < pictures.length; j++) {
                    try {
                        String bytes = imageUtil.imgToBase("goodspicture", pictures[j]);
                        picturesbyte.add(bytes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                goods.get(i).setPictures(picturesbyte);
            }
        }
        return CommonUtil.successJson(total,goods);
    }

    @Override
    public JSONObject setGoods(MultipartHttpServletRequest params, List<MultipartFile> files) throws IOException {

        String user_id = params.getParameter("user_id");
        String goods_name = params.getParameter("goods_name");
        String goods_price = params.getParameter("goods_price");
        String contact = params.getParameter("contact");
        String goods_number = params.getParameter("goods_number");
        String goods_cat = params.getParameter("goods_cat");
        String goods_introduce = params.getParameter("goods_introduce");

        MultipartFile file = null;
        ImageUtil imageUtil = new ImageUtil();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            String fileName = file.getOriginalFilename();
            String type = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".") + 1) : null;
            try {
                if (imageUtil.isImg(type)) {
                    //自定义的文件名称,避免文件名重复
                    String trueFileName = user_id +"--"+ String.valueOf(System.currentTimeMillis()) + "." + type;
                    File fileDir = imageUtil.getFile("goods");
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
        Goods goods = new Goods();
        goods.setUser_id(id);
        goods.setGoods_name(goods_name);
        goods.setGoods_price(goods_price);
        goods.setContact(contact);
        goods.setGoods_number(goods_number);
        goods.setGoods_cat(goods_cat);
        goods.setGoods_introduce(goods_introduce);
        goods.setGoods_pic(imgs);
        try {
            goodsDao.insertGoods(goods);
        } catch (Exception e) {
            return CommonUtil.errorJson(ErrorMsg.E_905);
        }
        return CommonUtil.successJson();
    }

    @Override
    public JSONObject deleteGoods(Integer id) {
        goodsDao.deleteGoods(id);
        return CommonUtil.successJson();
    }

    @Override
    public JSONObject updataGoods(JSONObject requestJson) {
        goodsDao.updataGoods(requestJson);
        return CommonUtil.successJson();
    }
}
