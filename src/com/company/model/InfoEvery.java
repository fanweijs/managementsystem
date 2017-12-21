package com.company.model;

/**
 * Created by 李宏观 on 2017/12/19.
 */
public class InfoEvery {
    private Integer id;
    private Integer infoid;
    private String accountid;
    private String condition;

    public InfoEvery() {
    }

    public InfoEvery(Integer id, Integer infoid, String accountid, String condition) {
        this.id = id;
        this.infoid = infoid;
        this.accountid = accountid;
        this.condition = condition;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInfoid() {
        return infoid;
    }

    public void setInfoid(Integer infoid) {
        this.infoid = infoid;
    }

    public String getAccountid() {
        return accountid;
    }

    public void setAccountid(String accountid) {
        this.accountid = accountid;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Infoevery{" +
                "id=" + id +
                ", infoid=" + infoid +
                ", accountid='" + accountid + '\'' +
                ", condition='" + condition + '\'' +
                '}';
    }
}
