package com.example.common;

public enum ApiMessage {

    /**
     * 成功
     */
    OK("00", "成功"),
    /**
     * 认证失败
     */
    UNAUTHORIZED("01", "认证失败"),
    /**
     * 参数错误
     */
    PARAM_ERROR("02", "参数错误"),
    /**
     * 权限不足
     */
    FORBIDDEN("03", "权限不足"),
    /**
     * 请求的资源不存在
     */
    NOT_FOUND("04", "请求的资源不存在"),
    /**
     * 服务器异常
     */
    SERVER_ERROR("05", "服务器异常");

    private final String code;
    private final String msg;

    ApiMessage(String code, String msg) {
        this.code=code;
        this.msg=msg;
    }

    public String code() {
        return this.code;
    }

    public String msg() {
        return this.msg;
    }

}
