package com.ls.dao;

import com.alibaba.fastjson.JSONObject;
import com.ls.pojo.user.UserDetail;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserDetailDao {

    List<UserDetail> getUserDetailList();

    JSONObject getUserDetailByID(Integer id);

    void insertUserDetail(UserDetail userDetail);

    void deleteUserDetail(Integer id);

    void updateUserRole(@Param("id") Integer id, @Param("role") String role);

    void updateUserState(@Param("id") Integer id, @Param("state") String role, @Param("locktime") Date locktime,@Param("unlocktime") Date unlocktime);

    List<UserDetail> getUnlockUser();

    void autoUpdataUserState(Integer id);
}
