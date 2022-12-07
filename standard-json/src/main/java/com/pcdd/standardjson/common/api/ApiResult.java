package com.pcdd.standardjson.common.api;

import lombok.Data;

import java.io.Serializable;

/**
 * @author PC
 * @version 1.0
 * @date 2021/8/13 20:10
 */
@Data
public class ApiResult<T> implements Serializable {

    private Integer code;
    private String msg;
    private T data;

    public static <T> ApiResult<T> success(T data) {
        return ApiResult.success(StatusCodeEnum.SC200.getMsg(), data);
    }

    public static <T> ApiResult<T> success(String msg, T data) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(StatusCodeEnum.SC200.getCode());
        apiResult.setMsg(msg);
        apiResult.setData(data);
        return apiResult;
    }

    public static <T> ApiResult<T> fail(Integer code, String msg) {
        ApiResult<T> apiResult = new ApiResult<>();
        apiResult.setCode(code);
        apiResult.setMsg(msg);
        return apiResult;
    }

}
