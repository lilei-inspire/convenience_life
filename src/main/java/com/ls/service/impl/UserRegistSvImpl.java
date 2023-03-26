package com.ls.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ls.dao.UserBaseDao;
import com.ls.dao.UserDetailDao;
import com.ls.pojo.user.UserBase;
import com.ls.pojo.user.UserDetail;
import com.ls.service.UserRegistSv;
import com.ls.util.common.CommonUtil;
import com.ls.util.common.EmailUtil;
import com.ls.util.constants.ErrorMsg;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserRegistSvImpl implements UserRegistSv {

    public static final transient Log log = LogFactory.getLog(UserRegistSvImpl.class);

    @Autowired
    UserBaseDao userBaseDao;
    @Autowired
    UserDetailDao userDetailDao;


    /**
     * 用户注册
     * */
    @Override
    public JSONObject uerRegist(JSONObject jsonObject) {
        String username = jsonObject.getString("name");
        String password = jsonObject.getString("pwd");
        String email = jsonObject.getString("email");
        UserBase userBase = new UserBase();
        userBase.setUser_name(username);
        userBase.setPassword(password);
        userBase.setEmail(email);
        userBase.setNick_name(username);
        try {
            userBaseDao.insertUserBase(userBase);
            Integer id = userBase.getUser_id();
            UserDetail userDetail = new UserDetail();
            userDetail.setUser_id(id);
            userDetail.setCreate_time(new Date());
            userDetailDao.insertUserDetail(userDetail);
            return CommonUtil.successJson();
        }catch (Exception e){
            log.error(e);
            return CommonUtil.errorJson(ErrorMsg.E_400);
        }

    }


    /**
     *发送验证码
     * */
    @Override
    public JSONObject verify(JSONObject jsonObject) throws Exception{

        EmailUtil emailUtil = new EmailUtil();
        String verifyCode = jsonObject.getString("code");
        String email = jsonObject.getString("email");
        try{
            emailUtil.send_email(email,verifyCode);
            return CommonUtil.successJson();
        }catch (Exception e){
            log.error(e);
            return CommonUtil.errorJson(ErrorMsg.E_902);
        }
    }
}
