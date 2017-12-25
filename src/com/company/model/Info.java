package com.company.model;


import java.sql.Date;

/**
 * Created by 李宏宇 on 2017/12/19.
 */
public class Info {
    private Integer id;
    private Date infotime;
    private String title;
    private String content;
    private Integer department;

    public Info(String s1, String s, String id, Object title, String content) {
    }

    public Info(Integer id, Date infotime, String title, String content, Integer department) {
        this.id = id;
        this.infotime = infotime;
        this.title = title;
        this.content = content;
        this.department = department;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInfotime() {
        return infotime;
    }

    public void setInfotime(Date infotime) {
        this.infotime = infotime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getDepartment() {
        return department;
    }

    public void setDepartment(Integer department) {
        this.department = department;
    }

    @Override
    public String toString() {
        String dept =department==1?"人事部":"财务部";
        return

                title  +"           "+

                        dept +"          "+infotime
                ;
    }
}
