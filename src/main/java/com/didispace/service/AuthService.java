package com.didispace.service;

import com.didispace.vo.TokenVo;

/**
 * Created with com.didispace.service.
 *
 * @author: Xavier
 * @time: 2019/6/6 17:18
 */
public interface AuthService {
    public TokenVo auth(String username, String password);
}
