package com.ls.dao;

import com.alibaba.fastjson.JSONObject;
import com.ls.pojo.goods.Goods;

import java.util.List;

public interface GoodsDao {

    List<Goods> getGoodsListByUserID(String name,Integer id);

    List<Goods> getGoodsList(String name);

    void insertGoods(Goods goods);

    void deleteGoods(Integer id);

    void updataGoods(JSONObject requestJson);

    void deleteAllGoods(Integer id);
}
