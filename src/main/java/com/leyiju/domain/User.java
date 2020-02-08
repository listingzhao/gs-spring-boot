package com.leyiju.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value="User",description="用户对象user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="ID",name="id", example = "1")
    private Long id;

    @ApiModelProperty(value="昵称",name="nickName")
    private String nickName;

    @ApiModelProperty(value="年龄",name="age", example = "12")
    private Integer age;



    @ApiModelProperty(value="手机号",name="username")
    private String phone;

    @ApiModelProperty(value="用户名",name="username")
    private String username;

    @ApiModelProperty(value="密码",name="password")
    private String password;
    @ApiModelProperty(value="角色",name="roles")
    private String roles;
    @ApiModelProperty(value="创建时间",name="createTime", example = "1575193318")
    private Long createTime;
    @ApiModelProperty(value="更新时间",name="updateTime", example = "1575193318")
    private Long updateTime;

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

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public User() {
        super();
        this.createTime = new Date().getTime();
        this.updateTime = new Date().getTime();
    }

    public User(String name, Integer age) {
        super();
        this.username = name;
        this.age = age;
        this.createTime = new Date().getTime();
        this.updateTime = new Date().getTime();

    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "userName " + this.username + ", age " + this.age;
    }
}
