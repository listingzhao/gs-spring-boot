package com.leyiju.service.handler;

import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with com.leyiju.service.handler.
 *
 * @author: Xavier
 * @time: 2019/12/3 22:31
 */
public class HandlerMethodReturnValueHandlerProxy implements HandlerMethodReturnValueHandler{

    private static final Integer STATUS_CODE_SUCCEEDED = 200;

    private HandlerMethodReturnValueHandler proxyObject;

    public HandlerMethodReturnValueHandlerProxy(HandlerMethodReturnValueHandler proxyObject) {
        this.proxyObject = proxyObject;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return proxyObject.supportsReturnType(returnType);
    }

    @Override
    public void handleReturnValue(Object o, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", STATUS_CODE_SUCCEEDED);
        resultMap.put("msg", "");
        resultMap.put("data", o);

        proxyObject.handleReturnValue(o, methodParameter, modelAndViewContainer, nativeWebRequest);
    }
}
