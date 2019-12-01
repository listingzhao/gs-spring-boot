package com.leyiju.service;

import com.leyiju.vo.TokenVo;

/**
 * Created with com.leyiju.service.
 *
 * @author: Xavier
 * @time: 2019/6/6 17:18
 */
public interface AuthService {
    public TokenVo auth(String username, String password);
}
