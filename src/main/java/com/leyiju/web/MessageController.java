package com.leyiju.web;

import com.leyiju.base.ResponseEntity;
import com.leyiju.enums.ResponseStatus;
import com.leyiju.exception.RestException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 *信息Controller
 */
@RestController
@RequestMapping(value = "/api/messages")
@Api(tags = "MessageAPI")
public class MessageController {

    @ApiOperation(value = "发送验证码", notes = "根据手机号发送验证码")
    @ApiImplicitParam(name = "phone", value = "手机号", dataType = "String", required = true)
    @RequestMapping(value = "code", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity sendMessage(@RequestParam(value = "phone", required = true) String phone) {
        Map<String, Object> data = new HashMap<>();
        if (StringUtils.isBlank(phone)) {
            throw new RestException(-1, "手机号不能为空");
        }
        return ResponseEntity.success(ResponseStatus.SUCCESS);
    }
}
