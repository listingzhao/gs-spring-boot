package com.leyiju.vo;

import com.leyiju.domain.User;
import io.swagger.annotations.ApiModelProperty;

public class UserVo {
    public UserVo(User user) {
        this.id = user.getId();
        this.age = user.getAge();
        this.nickName = user.getNickName();
        this.phone = user.getPhone();
        this.username = user.getUsername();
    }

    @ApiModelProperty(value="ID",name="id", example = "1")
    private Long id;

    @ApiModelProperty(value="昵称",name="nickName")
    private String nickName;

    @ApiModelProperty(value="年龄",name="age", example = "12")
    private Integer age;

    @ApiModelProperty(value="手机号",name="phone", example = "15100000000")
    private String phone;

    @ApiModelProperty(value="用户名",name="username")
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
