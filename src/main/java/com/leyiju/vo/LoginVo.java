package com.leyiju.vo;

import io.swagger.annotations.ApiModelProperty;

public class LoginVo {

    @ApiModelProperty(value="登录类型 account 手机号验证码登录 mobile 用户名密码登录",name="type", example = "account")
    private String type;

    @ApiModelProperty(value="验证码",name="code", example = "0000")
    private String code;

    @ApiModelProperty(value="用户名",name="username")
    private String username;

    @ApiModelProperty(value="密码",name="password")
    private String password;

    @ApiModelProperty(value="手机号",name="phone", example = "15100000000")
    private String phone;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
