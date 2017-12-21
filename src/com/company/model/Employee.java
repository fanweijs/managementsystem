package com.company.model;

import java.sql.Date;
import java.util.Arrays;

/**
 * Created by 高展 on 2017/12/19.
 */
public class Employee {
    private String account;
    private Integer departmentID;
    private String name;
    private byte[] avatar;
    private String sex;
    private Date birthday;
    private String position;
    private String maritalstatus;
    private String politiacal_status;//政治面貌
    private String education;//学历
    private String phone;
    private String native_province;
    private String native_city;
    private Date date;

    public Employee(String account, Integer departmentID, String name, byte[] avatar, String sex, Date birthday, String position, String maritalstatus, String politiacal_status, String education, String phone, String native_province, String native_city, Date date) {
        this.account = account;
        this.departmentID = departmentID;
        this.name = name;
        this.avatar = avatar;
        this.sex = sex;
        this.birthday = birthday;
        this.position = position;
        this.maritalstatus = maritalstatus;
        this.politiacal_status = politiacal_status;
        this.education = education;
        this.phone = phone;
        this.native_province = native_province;
        this.native_city = native_city;
        this.date = date;
    }

    public Employee() {

    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(Integer departmentID) {
        this.departmentID = departmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus;
    }

    public String getPolitiacal_status() {
        return politiacal_status;
    }

    public void setPolitiacal_status(String politiacal_status) {
        this.politiacal_status = politiacal_status;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNative_province() {
        return native_province;
    }

    public void setNative_province(String native_province) {
        this.native_province = native_province;
    }

    public String getNative_city() {
        return native_city;
    }

    public void setNative_city(String native_city) {
        this.native_city = native_city;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "account='" + account + '\'' +
                ", departmentID=" + departmentID +
                ", name='" + name + '\'' +
                ", avatar=" + Arrays.toString(avatar) +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", position='" + position + '\'' +
                ", maritalstatus='" + maritalstatus + '\'' +
                ", politiacal_status='" + politiacal_status + '\'' +
                ", education='" + education + '\'' +
                ", phone='" + phone + '\'' +
                ", native_province='" + native_province + '\'' +
                ", native_city='" + native_city + '\'' +
                ", date=" + date +
                '}';
    }
}
