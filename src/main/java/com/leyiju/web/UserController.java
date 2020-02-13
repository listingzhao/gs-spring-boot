package com.leyiju.web;

import com.leyiju.base.ResponseEntity;
import com.leyiju.domain.User;

import java.util.*;

import com.leyiju.enums.ResponseStatus;
import com.leyiju.service.UserService;
import io.swagger.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户Controller
 */
@RestController
@RequestMapping(value = "/api")
@Api(value = "UserAPI")
public class UserController {

    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());
    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户列表", notes = "")
    @ResponseBody
    @RequestMapping(value = {"/users/list"}, method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> getUserList() {
        List<User> r = userService.getUserList();
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> response = new HashMap<>();
        response.put("data", r);
        response.put("code", 200);
        return response;
    }

    @ApiOperation(value = "创建用户", notes = "根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity postUser(@RequestBody User user) {
        users.put(user.getId(), user);
        if(user != null) {
            userService.saveUser(user);
        }
        return ResponseEntity.success(ResponseStatus.SUCCESS, user);
    }

    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", example = "123", paramType = "path")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id) {

        return users.get(id);
    }

    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", example = "123", paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    })
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        User u = users.get(id);
        u.setUsername(user.getUsername());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long", example = "123", paramType = "path")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }

    @ApiOperation(value = "根据用户名查询用户", notes = "根据用户名查找用户")
    @ResponseBody
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String")
    @RequestMapping(value = "/users/username/{username}", method = RequestMethod.GET)
    public ResponseEntity getUserByUserName(@PathVariable String username) {
        Map<String, Object> response = new HashMap<>();
        User user = userService.getUserByUsername(username);
        return getStringObjectMap(response, user);
    }

    @ApiOperation(value = "根据手机号查询用户", notes = "根据手机号查询用户")
    @ResponseBody
    @ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "String")
    @RequestMapping(value = "/users/phone/{phone}", method = RequestMethod.GET)
    public ResponseEntity getUserByPhone(@PathVariable String phone) {
        Map<String, Object> response = new HashMap<>();
        User user = userService.getUserByPhone(phone);
        return getStringObjectMap(response, user);
    }

    @NotNull
    private ResponseEntity getStringObjectMap(Map<String, Object> response, User user) {
        if (user == null) {
            return ResponseEntity.error(ResponseStatus.ACCOUNT_NOT_EXIST);
        }
        return ResponseEntity.success(ResponseStatus.SUCCESS, user);
    }

}