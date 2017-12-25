package com.company.model;

import java.sql.Date;

/**
 * Created by 柏欢欢 on 2017/12/20.
 */
public class RewardPublish {
    private Integer id;
    private String account;
    private String flag;
    private String rp_name;
    private Date rp_time;

    public RewardPublish(Integer id, String account, String flag, String rp_name, Date rp_time) {
        this.id = id;
        this.account = account;
        this.flag = flag;
        this.rp_name = rp_name;
        this.rp_time = rp_time;
    }

    public RewardPublish() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getRp_name() {
        return rp_name;
    }

    public void setRp_name(String rp_name) {
        this.rp_name = rp_name;
    }

    public Date getRp_time() {
        return rp_time;
    }

    public void setRp_time(Date rp_time) {
        this.rp_time = rp_time;
    }

    @Override
    public String toString() {
        return   " "+ rp_name +"   "+ rp_time;
    }
}
