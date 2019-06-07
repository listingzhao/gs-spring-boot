package com.didispace.domain;

import java.io.Serializable;

/**
 * Created with com.didispace.domain.
 *
 * @author: Xavier
 * @time: 2019/6/6 15:04
 */
public class QQUser implements Serializable{

    private String nickname;
    private String gender;
    private String province;
    private String year;
    private String avatar;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
