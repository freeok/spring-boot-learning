package com.pcdd.standardjson.common.exception;

import com.pcdd.standardjson.common.api.ApiResult;
import com.pcdd.standardjson.common.api.StatusCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常处理
 * @author PC
 * @version 1.0
 * @date 2021/8/13 20:01
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 捕获404异常
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public ApiResult<String> handle(NoHandlerFoundException e) {
        log.error("全局异常信息：{}", e.getMessage());
        return ApiResult.fail(StatusCodeEnum.SC404.getCode(), StatusCodeEnum.SC404.getMsg());
    }

    /**
     * 捕获其他异常
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApiResult<String> handle(Exception e) {
        log.error("全局异常信息：{}", e.getMessage());
        return ApiResult.fail(StatusCodeEnum.SC500.getCode(), StatusCodeEnum.SC500.getMsg() + "：" + e.getMessage());
    }

}
