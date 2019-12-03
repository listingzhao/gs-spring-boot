package com.leyiju.service.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with com.leyiju.service.handler.
 *
 * @author: Xavier
 * @time: 2019/12/3 22:52
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public Map<String, Object> restError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        Map<String, Object> map = new HashMap<>();
//        map.put("exception", null != restException.getT() ? restException.getT() : restException);
//        map.put("errorMessage", restException.getMessage());
//        map.put("url", request.getRequestURL());
//        map.put("statusCode",  restException.getCode());
        return map;
    }
}
