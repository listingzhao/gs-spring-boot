package com.didispace.web;

import com.didispace.vo.TokenVo;
import com.didispace.domain.User;
import com.didispace.service.AuthService;
import com.didispace.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with com.didispace.web.
 *
 * @author: Xavier
 * @time: 2019/6/5 20:29
 */
@RestController
@RequestMapping(value = "/auth")
@Api(tags = "authAPI")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @ApiOperation(value = "注册用户", notes = "根据User对象注册用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String postUser(Model model, @Valid @ModelAttribute("form") User user) {
        if (user != null) {
            userService.saveUser(user);
        }
        return "success";
    }

    @ApiOperation(value = "用户登录", notes = "根据用户名密码登录")
    @ResponseBody
    @RequestMapping(value = "token", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String,Object> login(@RequestBody User user) {
        TokenVo tokenVo = authService.auth(user.getUsername(), user.getPassword());
        Map<String,Object> data = new HashMap<>();
        data.put("access_token", tokenVo.getAccessToken());
        data.put("token_type", tokenVo.getTokenType());
        data.put("auth_time", tokenVo.getAuthTime());
        data.put("expires_in", tokenVo.getExpiresIn());
        Map<String,Object> response = new HashMap<>();
        response.put("data", data);
        response.put("code", 200);
        return response;
    }
}
