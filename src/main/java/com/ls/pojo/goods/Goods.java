package com.ls.pojo.goods;

import java.util.List;

public class Goods {

    private int user_id;
    private int goods_id;
    private String goods_name;
    private String goods_price;
    private String goods_number;
    private String goods_cat;
    private String goods_pic;
    private String goods_introduce;
    private String add_time;
    private String contact;
    private String goods_seller;
    private List<String> pictures;

    public String getGoods_seller() {
        return goods_seller;
    }

    public void setGoods_seller(String goods_seller) {
        this.goods_seller = goods_seller;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getGoods_number() {
        return goods_number;
    }

    public void setGoods_number(String goods_number) {
        this.goods_number = goods_number;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGoods_cat() {
        return goods_cat;
    }

    public void setGoods_cat(String goods_cat) {
        this.goods_cat = goods_cat;
    }

    public String getGoods_pic() {
        return goods_pic;
    }

    public void setGoods_pic(String goods_pic) {
        this.goods_pic = goods_pic;
    }

    public String getGoods_introduce() {
        return goods_introduce;
    }

    public void setGoods_introduce(String goods_introduce) {
        this.goods_introduce = goods_introduce;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }
}
