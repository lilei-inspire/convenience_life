package com.ls.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ls.dao.CircleDao;
import com.ls.dao.GoodsDao;
import com.ls.dao.UserBaseDao;
import com.ls.dao.UserDetailDao;
import com.ls.pojo.circle.Dynamic;
import com.ls.pojo.user.UserBase;
import com.ls.pojo.user.UserManage;
import com.ls.service.UserManageSv;
import com.ls.util.common.CommonUtil;
import com.ls.util.constants.ErrorMsg;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Service
public class UserManageSvImpl implements UserManageSv {

    public static final transient Log log = LogFactory.getLog(UserManageSvImpl.class);

    @Autowired
    UserBaseDao userBaseDao;
    @Autowired
    UserDetailDao userDetailDao;
    @Autowired
    GoodsDao goodsDao;
    @Autowired
    CircleDao circleDao;


    @Override
    public JSONObject getUser(Integer id) {
        JSONObject jsonObject = userBaseDao.getUserByID(id);
        return CommonUtil.successJson(jsonObject);
    }

    @Override
    public JSONObject deleteUserBase(Integer id) {
        try{
            userBaseDao.deleteUserBase(id);
            userDetailDao.deleteUserDetail(id);
            goodsDao.deleteAllGoods(id);
            circleDao.deleteAllDynamic(id);

//            List<Dynamic> dynamics = circleDao.getDynamicListByUserID(id);
//            int[] dynamicid = new int[dynamics.size()];
//            for(int i=0;i<dynamics.size();i++){
//                dynamicid[i] = dynamics.get(i).getUser_id();
//            }
//            circleDao.deleteAllComment(dynamicid);
//            circleDao.deleteAllLike(dynamicid);
        }catch (Exception e){
            log.error(e);
            return CommonUtil.errorJson(ErrorMsg.E_910);
        }
        return CommonUtil.successJson();
    }

    @Override
    public JSONObject updataUserBase(UserBase userBase) {
        userBaseDao.updateUserBase(userBase, userBase.getUser_id());
        return CommonUtil.successJson();
    }

    @Override
    public JSONObject getUserList(String query) {
        List<UserBase> userBases = userBaseDao.getUserBaseList(query);
        Integer total = userBases.size();
        List<UserManage> userManages = new ArrayList<>();
        JSONObject jsonObject = null;
        for (int i = 0; i < total; i++) {
            Integer id = userBases.get(i).getUser_id();
            jsonObject = userDetailDao.getUserDetailByID(id);
            UserManage userManage = new UserManage();
            userManage.setUser_id(id);
            userManage.setUser_name(userBases.get(i).getUser_name());
            userManage.setNick_name(userBases.get(i).getNick_name());
            userManage.setSex(userBases.get(i).getSex());
            userManage.setEmail(userBases.get(i).getEmail());
            userManage.setCreate_time(jsonObject.getString("create_time"));
            userManage.setUser_role(jsonObject.getString("user_role"));
            userManage.setUser_state(jsonObject.getString("user_state"));
            userManage.setLock_time(jsonObject.getString("lock_time"));
            userManage.setUnlock_time(jsonObject.getString("unlock_time"));
            userManages.add(userManage);
        }
        return CommonUtil.successJson(total,userManages);
    }

    @Override
    public JSONObject updataUserRole(Integer id,String role) {
        try{
            userDetailDao.updateUserRole(id,role);
        }catch (Exception e){
            log.error(e);
            return CommonUtil.errorJson(ErrorMsg.E_908);
        }

        return CommonUtil.successJson();
    }

    @Override
    public JSONObject updataUserState(Integer id,String state) {
        Date locktime = null;
        Date unlocktime = null;
        String userstate = null;
        try{
            if(state.equals("1")){
                Calendar calendar = Calendar.getInstance();
                locktime = calendar.getTime();
                calendar.add(Calendar.WEEK_OF_YEAR, 1);
                unlocktime = calendar.getTime();
                userDetailDao.updateUserState(id,state,locktime,unlocktime);
                userstate = "1";
            }else if(state.equals("0")){
                userDetailDao.updateUserState(id,state,locktime,unlocktime);
                userstate = "0";
            }
        }catch (Exception e){
            log.error(e);
            return CommonUtil.errorJson(ErrorMsg.E_908);
        }
        return CommonUtil.successJson(userstate);
    }

}
