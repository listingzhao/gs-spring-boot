package com.didispace.domain;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value="user对象",description="用户对象user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @ApiModelProperty(value="昵称",name="nickName")
    private String nickName;
    @ApiModelProperty(value="年龄",name="age")
    private Integer age;
    @ApiModelProperty(value="用户名",name="username")
    private String username;
    @ApiModelProperty(value="密码",name="password")
    private String password;
    @ApiModelProperty(value="角色",name="roles")
    private String roles;
    private Long createTime;
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

    @Override
    public String toString() {
        return "userName " + this.username + ", age " + this.age;
    }
}
