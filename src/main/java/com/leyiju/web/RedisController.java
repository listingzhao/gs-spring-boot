package com.leyiju.web;

import com.leyiju.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created with com.leyiju.web.
 *
 * @author: Xavier
 * @time: 2019/3/25 11:20
 */
@RestController
@RequestMapping(value = "/redis")
@Api(tags = "redis 测试API")
public class RedisController {

    @Autowired
    RedisUtil redisUtil;

    @GetMapping("set/{key}/{value}")
    @ApiOperation(value = "设置缓存")
    public String set(@PathVariable("key") String key, @PathVariable("value") String value) {
        redisUtil.set(key, value);
        return key + "," + value;
    }

    @GetMapping("get/{key}")
    @ApiOperation(value = "根据key获取缓存")
    public String get(@PathVariable("key") String key) {
        return "key=" + key + ",value=" + redisUtil.get(key);
    }
}
