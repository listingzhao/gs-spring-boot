package com.didispace.web;

import com.didispace.domain.User;
import com.didispace.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created with com.didispace.web.
 *
 * @author: Xavier
 * @time: 2019/6/5 20:29
 */
@RestController
@RequestMapping(value="/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @ApiOperation(value="注册用户", notes="根据User对象注册用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value="register", method= RequestMethod.POST)
    public String postUser(Model model, @Valid @ModelAttribute("form") User user) {
        if(user != null) {
            userService.saveUser(user);
        }
        return "success";
    }
}
