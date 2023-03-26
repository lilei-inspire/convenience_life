package com.ls.service.impl;

import com.ls.dao.UserDetailDao;
import com.ls.pojo.user.UserDetail;
import com.ls.service.ScheduledSv;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
public class ScheduleSvImpl implements ScheduledSv {

    public static final transient Log log = LogFactory.getLog(ScheduleSvImpl.class);

    @Autowired
    UserDetailDao userDetailDao;

    @Override
    @Scheduled(cron = "0 0/1 * * * ? ")
    public void unLockUser() {
        List<UserDetail> userDetailList = userDetailDao.getUnlockUser();
        int s = 0;
        for (int i = 0; i < userDetailList.size(); i++) {
            Date unlocktime = userDetailList.get(i).getUnlock_time();
            Date systime = new Date();
            if (systime.after(unlocktime)) {
                Integer userid = userDetailList.get(i).getUser_id();
                userDetailDao.autoUpdataUserState(userid);
                s++;
            }
        }

    }
}
