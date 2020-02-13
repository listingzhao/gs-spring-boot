package com.leyiju.service.handler;

import com.leyiju.exception.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ModelAttribute
    public void addAttribute(Model model) {
        model.addAttribute("attribute", "The Attribute");
    }

    @ExceptionHandler({RestException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> restError(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        RestException restException = (RestException) ex;
        Map<String, Object> map = new HashMap<>();
        map.put("msg", restException.getMessage());
        map.put("url", request.getRequestURL());
        map.put("status", restException.getCode());
        return map;
    }
}
