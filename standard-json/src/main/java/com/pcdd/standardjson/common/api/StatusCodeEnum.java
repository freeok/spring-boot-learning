package com.pcdd.standardjson.common.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 自定义状态码
 *
 * @author PC
 * @version 1.0
 * @date 2021/8/13 22:37
 */
@SuppressWarnings("AlibabaEnumConstantsMustHaveComment")
@AllArgsConstructor
@Getter
public enum StatusCodeEnum {

    SC200(200, "操作成功"),
    SC999(999, "操作失败"),
    SC401(401, "匿名用户访问权限资源时的异常"),
    SC403(403, "无访问权限，请联系管理员授予权限"),
    SC404(404, "请求的资源不存在"),
    SC500(500, "系统异常，请稍后重试"),

    SC1001(1001, "客户端认证失败"),
    SC1002(1002, "用户名或密码错误"),
    SC1003(1003, "不支持的认证模式"),

    SC2001(2001, "访问令牌不合法"),
    SC2003(2003, "没有权限访问该资源"),

    SC3000(3000, "服务开启限流保护，请稍后再试！"),
    SC3001(3001, "服务开启降级保护，请稍后再试！"),
    SC3002(3002, "热点参数限流，请稍后再试！"),
    SC3003(3003, "系统规则不满足要求，请稍后再试！"),
    SC3004(3004, "授权规则不通过，请稍后再试！");

    private final Integer code;
    private final String msg;

}
