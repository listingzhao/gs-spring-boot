package com.leyiju.web;

import com.leyiju.exception.RestException;
import com.leyiju.vo.TokenVo;
import com.leyiju.domain.User;
import com.leyiju.service.AuthService;
import com.leyiju.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with com.leyiju.web.
 *
 * @author: Xavier
 * @time: 2019/6/5 20:29
 */
@RestController
@RequestMapping(value = "/api/auth")
@Api(tags = "authAPI")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @ApiOperation(value = "注册用户", notes = "根据User对象注册用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", dataType = "User")
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String postUser(Model model, @Valid @ModelAttribute("form") User user) {
        if (user != null) {
            userService.saveUser(user);
        }
        return "success";
    }

    @ApiOperation(value = "用户登录", notes = "根据用户名密码登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "登录类型 1 手机号验证码登录 2 用户名密码登录", dataType = "Integer"),
            @ApiImplicitParam(name = "phone", value = "登录手机号", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "验证码", dataType = "String"),
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String")
    })
    @ResponseBody
    @RequestMapping(value = "token", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> token(@RequestParam(value = "type", required = true) Integer type,
                                     @RequestParam(value = "username", required = false) String username,
                                     @RequestParam(value = "password", required = false) String password,
                                     @RequestParam(value = "phone", required = false) String phone,
                                     @RequestParam(value = "code", required = false) String code) {

        TokenVo tokenVo = null;
        Map<String, Object> data = new HashMap<>();
        if (type == 2) { // 用户名密码登录
            if (StringUtils.isBlank(username)) {
                throw new RestException(-1, "用户名不能为空");
            }
            if (StringUtils.isBlank(password)) {
                throw new RestException(-1, "密码不能为空");
            }
            tokenVo = authService.auth(username, password);
        } else {

        }

        if (tokenVo == null) {
            throw new RestException(-1, "用户名或密码错误");
        }

        data.put("access_token", tokenVo.getAccessToken());
        data.put("token_type", tokenVo.getTokenType());
        data.put("auth_time", tokenVo.getAuthTime());
        data.put("expires_in", tokenVo.getExpiresIn());
        return data;
    }

    @ApiOperation(value = "刷新token", notes = "根据旧token")
    @ResponseBody
    @RequestMapping(value = "refreshToken", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> refreshToken(@RequestParam(value = "token") String token) {
        Map<String, Object> response = new HashMap<>();
        TokenVo tokenVo = authService.refreshToken(token);
        if (null == tokenVo) {
            throw new RestException(-1, "token未过期");
        }
        Map<String, Object> data = new HashMap<>();
        data.put("access_token", tokenVo.getAccessToken());
        data.put("token_type", tokenVo.getTokenType());
        data.put("auth_time", tokenVo.getAuthTime());
        data.put("expires_in", tokenVo.getExpiresIn());
        return data;
    }
}
