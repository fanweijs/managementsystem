package com.company.model;

/**
 * @author  樊委
 * 2017-12-18
 */
public class Login {
    private String account;
    private String password;

    public Login(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
