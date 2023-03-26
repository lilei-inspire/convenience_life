package com.ls.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ls.dao.MenuListDao;
import com.ls.dao.UserBaseDao;
import com.ls.dao.UserDetailDao;
import com.ls.pojo.menu.Menu;
import com.ls.service.UserLoginSv;
import com.ls.util.common.CommonUtil;
import com.ls.util.common.ImageUtil;
import com.ls.util.constants.ErrorMsg;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class UserLoginSvImpl implements UserLoginSv {

    public static final transient Log log = LogFactory.getLog(UserLoginSvImpl.class);

    @Autowired
    UserBaseDao userBaseDao;
    @Autowired
    MenuListDao menuListDao;
    @Autowired
    UserDetailDao userDetailDao;


    @Override
    public JSONObject userLogin(JSONObject json) {
        String username = json.getString("username");
        String password = json.getString("password");

        try {
            JSONObject jsonObject = userBaseDao.getUserByUserName(username);
            String pwd = jsonObject.getString("password");
            Integer user_id = jsonObject.getInteger("user_id");
            JSONObject jsonObject1 = userDetailDao.getUserDetailByID(user_id);
            if(jsonObject1.getInteger("user_state")==1){
                return CommonUtil.errorJson(ErrorMsg.E_907);
            }
            if (password.equals(pwd)) {
                JSONObject resJson = CommonUtil.successJson();
                resJson.put("token", "asd158wd1d12d21");
                resJson.put("user_id", user_id);
                return resJson;
            } else {
                return CommonUtil.errorJson(ErrorMsg.E_903);
            }
        } catch (Exception e) {
            log.error(e);
            return CommonUtil.errorJson(ErrorMsg.E_900);
        }

    }

    @Override
    public JSONObject getMenu(Integer id) {
        List<Menu> menuList = menuListDao.getMenuList();
        JSONObject jsonObject = userDetailDao.getUserDetailByID(id);
        String role = jsonObject.getString("user_role");
        if(role.equals("user")){
            menuList.remove(menuList.size()-1);
        }
        return CommonUtil.successJson(menuList);
    }

    @Override
    public JSONObject getHead(JSONObject json) throws IOException {
        ImageUtil imageUtil = new ImageUtil();
        String id = json.getString("userid");
        Integer uid = Integer.parseInt(id);
        JSONObject json1 = userBaseDao.getUserByID(uid);
        String headimg = json1.getString("head");
        String nickname = json1.getString("nick_name");
        JSONObject jsonObject = new JSONObject();
//        if (nickname == null || nickname.length() ==0) {
//            nickname = json1.getString("user_name");
//        }
        jsonObject.put("nick_name",nickname);
        jsonObject.put("head", imageUtil.imgToBase("head", headimg));
        return jsonObject;
    }
}
