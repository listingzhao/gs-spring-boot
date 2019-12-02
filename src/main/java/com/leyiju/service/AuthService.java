package com.leyiju.service;

import com.leyiju.vo.TokenVo;

/**
 * Created with com.leyiju.service.
 *
 * @author: Xavier
 * @time: 2019/6/6 17:18
 */
public interface AuthService {
    /**
     * 登录授权
     * @param username
     * @param password
     * @return
     */
    public TokenVo auth(String username, String password);

    /**
     * 刷新Token
     * @param oldToken
     * @return
     */
    public TokenVo refreshToken(String oldToken);
}
