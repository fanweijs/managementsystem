package com.company.model;

import java.sql.Date;

/**
 * @author 樊委
 * 2017-12-21
 */
public class Salary {
    private String account;
    private Date sdate;  //哪个月的工资
    private int poistion_level;//职位等级
    private double baseSalary;//基本工资
    private double levelSalary;//等级工资
    private double allChecking;//全勤奖
    private double subsidy; //补贴 出差补贴 + 工龄补贴
    private double sSalary;//应发工资
    private double leaveCut;//各项减款
    private double selfInsurance;//个人保险
    private double tax;//税收
    private double tSalary;//实发工资
    private String flag;

    public Salary(String account, Date sdate, int poistion_level, double baseSalary,
                  double levelSalary, double allChecking, double subsidy, double sSalary,
                  double leaveCut, double selfInsurance, double tax, double tSalary, String flag) {
        this.account = account;
        this.sdate = sdate;
        this.poistion_level = poistion_level;
        this.baseSalary = baseSalary;
        this.levelSalary = levelSalary;
        this.allChecking = allChecking;
        this.subsidy = subsidy;
        this.sSalary = sSalary;
        this.leaveCut = leaveCut;
        this.selfInsurance = selfInsurance;
        this.tax = tax;
        this.tSalary = tSalary;
        this.flag = flag;
    }

    public Salary() {
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public int getPoistion_level() {
        return poistion_level;
    }

    public void setPoistion_level(int poistion_level) {
        this.poistion_level = poistion_level;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getLevelSalary() {
        return levelSalary;
    }

    public void setLevelSalary(double levelSalary) {
        this.levelSalary = levelSalary;
    }

    public double getAllChecking() {
        return allChecking;
    }

    public void setAllChecking(double allChecking) {
        this.allChecking = allChecking;
    }

    public double getSubsidy() {
        return subsidy;
    }

    public void setSubsidy(double subsidy) {
        this.subsidy = subsidy;
    }

    public double getsSalary() {
        return sSalary;
    }

    public void setsSalary(double sSalary) {
        this.sSalary = sSalary;
    }

    public double getLeaveCut() {
        return leaveCut;
    }

    public void setLeaveCut(double leaveCut) {
        this.leaveCut = leaveCut;
    }

    public double getSelfInsurance() {
        return selfInsurance;
    }

    public void setSelfInsurance(double selfInsurance) {
        this.selfInsurance = selfInsurance;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double gettSalary() {
        return tSalary;
    }

    public void settSalary(double tSalary) {
        this.tSalary = tSalary;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "account='" + account + '\'' +
                ", sdate=" + sdate +
                ", poistion_level=" + poistion_level +
                ", baseSalary=" + baseSalary +
                ", levelSalary=" + levelSalary +
                ", allChecking=" + allChecking +
                ", subsidy=" + subsidy +
                ", sSalary=" + sSalary +
                ", leaveCut=" + leaveCut +
                ", selfInsurance=" + selfInsurance +
                ", tax=" + tax +
                ", tSalary=" + tSalary +
                ", flag='" + flag + '\'' +
                '}';
    }
}
