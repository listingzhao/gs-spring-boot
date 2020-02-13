package com.leyiju.vo;

import com.leyiju.domain.Menu;
import com.leyiju.domain.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created with com.leyiju.
 *
 * @author: Xavier
 * @time: 2019/6/7 14:11
 */
@ApiModel(value="TokenVo",description="用户对象user")
public class TokenVo {
    @ApiModelProperty(value="Token",name="accessToken")
    private String accessToken;
    private String tokenType;
    private Long authTime;
    private Long expiresIn;
    private UserVo userInfo;
    private List<Menu> menus;
    private List<String> roles;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Long getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Long authTime) {
        this.authTime = authTime;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public UserVo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserVo userInfo) {
        this.userInfo = userInfo;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
