package com.leyiju.service.impl;

import com.leyiju.vo.TokenVo;
import com.leyiju.service.AuthService;
import com.leyiju.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created with com.leyiju.service.impl.
 *
 * @author: Xavier
 * @time: 2019/6/6 17:19
 */
@Component
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public TokenVo auth(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String token = jwtTokenUtil.generateToken(userDetails);
        TokenVo tokenVo = new TokenVo();
        tokenVo.setAccessToken(token);
        tokenVo.setAuthTime(new Date().getTime());
        tokenVo.setExpiresIn(jwtTokenUtil.getExpirationDateFromToken(token).getTime());
        tokenVo.setTokenType("Bearer");
        return tokenVo;
    }

    @Override
    public TokenVo refreshToken(String oldToken) {
        String token = oldToken.substring("Bearer ".length());
        TokenVo tokenVo = new TokenVo();
        if (jwtTokenUtil.isTokenExpired(token)) {
            String newToken = jwtTokenUtil.refreshToken(token);
            tokenVo.setAccessToken(newToken);
            tokenVo.setAuthTime(new Date().getTime());
            tokenVo.setExpiresIn(jwtTokenUtil.getExpirationDateFromToken(token).getTime());
            tokenVo.setTokenType("Bearer");
            return tokenVo;
        }
        return null;
    }
}
