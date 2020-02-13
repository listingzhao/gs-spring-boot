package com.leyiju.service;

import com.leyiju.domain.GroupRole;
import com.leyiju.domain.UserRole;
import com.leyiju.vo.TokenVo;

import java.util.List;

/**
 * Created with com.leyiju.service.
 *
 * @author: Xavier
 * @time: 2019/6/6 17:18
 */
public interface AuthService {
    /**
     * 登录账户授权
     * @param username
     * @param password
     * @return
     */
    public TokenVo auth(String username, String password);

    /**
     * 登录手机号授权
     * @param phone
     * @return
     */
    public TokenVo authByPhone(String phone);

    /**
     * 刷新Token
     * @param oldToken
     * @return
     */
    public TokenVo refreshToken(String oldToken);

    /**
     * 获取用户角色列表
     * @param userId
     * @return
     */
    public List<UserRole> getUserRolesByUserId(Long userId);

    /**
     * 获取角色权限组列表
     * @param roleId
     * @return
     */
    public List<GroupRole> getGroupWithRolesByRoleId(Long roleId);
}
