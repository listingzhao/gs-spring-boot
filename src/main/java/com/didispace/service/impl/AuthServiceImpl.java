package com.didispace.service.impl;

import com.didispace.TokenVo;
import com.didispace.service.AuthService;
import com.didispace.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created with com.didispace.service.impl.
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
}
