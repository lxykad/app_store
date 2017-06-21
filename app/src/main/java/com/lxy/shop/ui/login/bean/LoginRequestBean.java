package com.lxy.shop.ui.login.bean;

/**
 * Created by lxy on 2017/6/20.
 */

public class LoginRequestBean {

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
