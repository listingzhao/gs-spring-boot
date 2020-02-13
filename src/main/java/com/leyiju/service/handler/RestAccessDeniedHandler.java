package com.leyiju.service.handler;

import com.alibaba.fastjson.JSONObject;
import com.leyiju.base.ResponseEntity;
import com.leyiju.enums.ResponseStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with com.leyiju.service.handler.
 *
 * @author: Xavier
 * @time: 2019/12/1 14:04
 */
public class RestAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setStatus(403);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        JSONObject o = new JSONObject();
        o.put("stauts", ResponseStatus.NO_PERMISSIONS.getStatus());
        o.put("msg", ResponseStatus.NO_PERMISSIONS.getMsg());
        httpServletResponse.getWriter().print(o.toJSONString());
    }
}
