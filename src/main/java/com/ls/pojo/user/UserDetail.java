package com.ls.pojo.user;

import java.util.Date;

public class UserDetail {

    private int user_id;
    private String user_role;
    private String user_state;
    private Date create_time;
    private Date lock_time;
    private Date unlock_time;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getUser_state() {
        return user_state;
    }

    public void setUser_state(String user_state) {
        this.user_state = user_state;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getLock_time() {
        return lock_time;
    }

    public void setLock_time(Date lock_time) {
        this.lock_time = lock_time;
    }

    public Date getUnlock_time() {
        return unlock_time;
    }

    public void setUnlock_time(Date unlock_time) {
        this.unlock_time = unlock_time;
    }
}
