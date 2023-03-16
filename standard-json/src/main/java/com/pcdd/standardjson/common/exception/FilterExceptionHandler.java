package com.pcdd.standardjson.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @author pcdd
 * @date 2021/8/13 21:17
 */
@RestController
public class FilterExceptionHandler implements ErrorController {

    @RequestMapping("/error")
    public void handleError(HttpServletRequest request) throws Throwable {
        Object attribute = request.getAttribute("javax.servlet.error.exception");
        if (attribute != null) {
            throw (Throwable) attribute;
        } else {
            // 说明是404异常，抛给全局异常处理
            throw new NoHandlerFoundException("", "", null);
        }
    }

}
