package com.example.common;

import lombok.Data;

/**
 * TODO
 *
 * @author 余新建
 * @date 2020/2/19 11:28
 */
@Data
public class BaseResponse {
    private int code;
    private String msg;

    public static BaseResponse Ok(String msg){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(0);
        baseResponse.setMsg(msg);
        return baseResponse;
    }

    public static BaseResponse fail(String msg){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(1);
        baseResponse.setMsg(msg);
        return baseResponse;
    }

    public static BaseResponse noLogin(){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(403);
        baseResponse.setMsg("未登陆,请先登陆");
        return baseResponse;
    }
}
