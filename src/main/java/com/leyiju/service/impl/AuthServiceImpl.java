package com.leyiju.service.impl;

import com.leyiju.domain.*;
import com.leyiju.mapper.AuthorMapper;
import com.leyiju.mapper.GroupRoleMapper;
import com.leyiju.mapper.MenuMapper;
import com.leyiju.mapper.UserRoleMapper;
import com.leyiju.service.UserService;
import com.leyiju.vo.TokenVo;
import com.leyiju.service.AuthService;
import com.leyiju.utils.JwtTokenUtil;
import com.leyiju.vo.UserVo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private GroupRoleMapper groupRoleMapper;

    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public TokenVo auth(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        return getTokenVo(userDetails);
    }

    @Override
    public TokenVo authByPhone(String phone) {
        User user = userService.getUserByPhone(phone);
        if (null != user) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            return getTokenVo(userDetails);
        }
        return null;
    }

    @NotNull
    private TokenVo getTokenVo(UserDetails userDetails) {
        String token = jwtTokenUtil.generateToken(userDetails);
        TokenVo tokenVo = new TokenVo();
        tokenVo.setAccessToken(token);
        tokenVo.setAuthTime(new Date().getTime());
        tokenVo.setExpiresIn(jwtTokenUtil.getExpirationDateFromToken(token).getTime());
        tokenVo.setTokenType("Bearer");

        // 获取用户角色列表
        User user = userService.getUserByUsername(userDetails.getUsername());
        if(user !=null) {
            List<String> roles = new ArrayList<>();
            // 角色组
            List<UserRole> userRoles = this.getUserRolesByUserId(user.getId());

            // 资源组
            List<Author> authors = new ArrayList<>();

            for (UserRole userRole : userRoles) {
                List<Author> auts = authorMapper.getAuthorsByRoleId(userRole.getRoleId());
                authors.addAll(auts);
                roles.add(userRole.getRoles().getRole());
            }

            // 菜单列表
            List<Menu> menus = new ArrayList<>();
            for (Author author: authors) {
                // 菜单资源
                if(author.getResourceType().equals("1")) {
                    List<Menu> ms = menuMapper.getMenusById(author.getResourceId());
                    menus.addAll(ms);
                }
            }

            tokenVo.setRoles(roles);
            tokenVo.setMenus(menus);
            tokenVo.setUserInfo(new UserVo(user));
        }
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

    @Override
    public List<UserRole> getUserRolesByUserId(Long userId) {
        return userRoleMapper.selectUserWithRoles(userId);
    }

    @Override
    public List<GroupRole> getGroupWithRolesByRoleId(Long roleId) {
        return groupRoleMapper.getGroupWithRoles(roleId);
    }
}
