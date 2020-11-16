package com.example.common;

import lombok.Data;

/**
 * @author zhangy
 * @Time 2020/4/3 23:44
 */
@Data
public class ApiResponse<T> {

    public static final ApiResponse OK= new ApiResponse(ApiMessage.OK);

    private String code;
    private String message;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResponse(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(ApiMessage apiMessage) {
        this.code = apiMessage.code();
        this.message = apiMessage.msg();
    }

    public ApiResponse(ApiMessage apiMessage, T data) {
        this.code = apiMessage.code();
        this.message = apiMessage.msg();
        this.data = data;
    }

}
