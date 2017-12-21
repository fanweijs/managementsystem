package com.company.model;

import java.sql.Date;

/**
 * Created by 咸赛 on 2017/12/20.
 */
public class Checking {
    private Integer check_id;
    private String account;
    private Date datetime;
    private String condition;

    public Checking(Integer check_id, String account, Date datetime, String condition) {
        this.check_id = check_id;
        this.account = account;
        this.datetime = datetime;
        this.condition = condition;
    }

    public Checking() {
    }

    public Integer getCheck_id() {
        return check_id;
    }

    public void setCheck_id(Integer check_id) {
        this.check_id = check_id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Checking{" +
                "check_id=" + check_id +
                ", account='" + account + '\'' +
                ", datetime=" + datetime +
                ", condition='" + condition + '\'' +
                '}';
    }
}
