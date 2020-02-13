package com.leyiju.service.handler;

import com.alibaba.fastjson.JSONObject;
import com.leyiju.enums.ResponseStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with com.leyiju.service.handler.
 *
 * @author: Xavier
 * @time: 2019/12/1 14:01
 */
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint{
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setStatus(401);
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        JSONObject o = new JSONObject();
        o.put("stauts", ResponseStatus.NO_UNAUTHORIZED.getStatus());
        o.put("msg", ResponseStatus.NO_UNAUTHORIZED.getMsg());
        httpServletResponse.getWriter().print(o.toJSONString());
    }
}
