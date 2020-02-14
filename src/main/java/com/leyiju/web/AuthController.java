package com.leyiju.web;

import com.leyiju.base.BaseResponseEntity;
import com.leyiju.base.ErrorResponseEntity;
import com.leyiju.base.ResponseEntity;
import com.leyiju.enums.ResponseStatus;
import com.leyiju.exception.RestException;
import com.leyiju.vo.LoginVo;
import com.leyiju.vo.RegisterVo;
import com.leyiju.vo.TokenVo;
import com.leyiju.domain.User;
import com.leyiju.service.AuthService;
import com.leyiju.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @ApiOperation(value = "注册用户", notes = "根据User对象注册用户")
    @ResponseBody
    @RequestMapping(value = "register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity postUser(@RequestBody @Valid RegisterVo vo) {
        // TODO: 根据验证码验证手机号
        User user = new User();
        System.out.println(vo.getCode());
        if (StringUtils.isNotBlank(vo.getCode()) && "0000".equals(vo.getCode())) {
            if (null != userService.getUserByUsername(vo.getUsername())) {
                return ResponseEntity.error(ResponseStatus.ACCOUNT_IS_EXISTENT);
            }
            if (null != userService.getUserByPhone(vo.getPhone())) {
                return ResponseEntity.error(ResponseStatus.PHONE_IS_EXISTENT);
            }
            if (vo != null) {
                user.setUsername(vo.getUsername());
                user.setPhone(vo.getPhone());
                user.setPassword(vo.getPassword());
                userService.saveUser(user);
            }
        } else {
            return ResponseEntity.error(ResponseStatus.ACCOUNT_IS_PHONECODE_ERROR);
        }

        return ResponseEntity.success(ResponseStatus.SUCCESS, user);
    }

    @ApiOperation(value = "用户登录", notes = "根据用户名密码登录")
    @ResponseBody
    @RequestMapping(value = "login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity token(@RequestBody LoginVo vo) {
        TokenVo tokenVo = null;
        if ("account".equals(vo.getType())) { // 用户名密码登录
            if (StringUtils.isBlank(vo.getUsername()) || StringUtils.isBlank(vo.getPassword())) {
                return ResponseEntity.error(ResponseStatus.PARAMS_NOT_IS_BLANK);
            }
            try {
                tokenVo = authService.auth(vo.getUsername(), vo.getPassword());
            } catch (Exception e) {
                e.printStackTrace();
                logger.error(ResponseStatus.ACCOUNT_LOGIN_ERROR.getMsg());
                return ResponseEntity.error(ResponseStatus.ACCOUNT_LOGIN_ERROR);
            }
        } else {
            if (vo.getCode().equals("0000")) {
                tokenVo = authService.authByPhone(vo.getPhone());
            } else {
                return ResponseEntity.error(ResponseStatus.ACCOUNT_IS_PHONECODE_ERROR);
            }
        }

        if (null == tokenVo) {
            return ResponseEntity.error(ResponseStatus.ACCOUNT_NOT_EXIST);
        }

        return ResponseEntity.success(ResponseStatus.SUCCESS, tokenVo);
    }

    @ApiOperation(value = "刷新token", notes = "根据旧token")
    @ResponseBody
    @RequestMapping(value = "refreshToken", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public ResponseEntity refreshToken(@RequestParam(value = "token") String token) {
        Map<String, Object> response = new HashMap<>();
        TokenVo tokenVo = authService.refreshToken(token);
        if (null == tokenVo) {
            throw new RestException(-1, "token未过期");
        }
        return ResponseEntity.success(ResponseStatus.SUCCESS, tokenVo);
    }
}
