package com.pcdd.standardjson.common.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 拦截Controller方法的返回值，统一处理响应体
 *
 * @author PC
 * @version 1.0
 * @date 2021/8/13 20:19
 */
@RestControllerAdvice
public class ApiResultWrapper implements ResponseBodyAdvice<Object> {

    /**
     * 是否支持advice功能
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        // 如果接口返回的类型本身就是ApiResult就不用进行包装
        return !methodParameter.getParameterType().equals(ApiResult.class);
    }

    /**
     * 对返回的数据进行处理
     */
    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o instanceof String) {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(ApiResult.success(o));
        }
        return ApiResult.success(o);
    }

}
