package com.example.common;

import lombok.Data;

/**
 * TODO
 *
 * @author 余新建
 * @date 2020/2/19 11:29
 */
@Data
public class DataResponse extends BaseResponse {
    private Object data;

    public static DataResponse Ok(Object data){
        DataResponse dataResponse = new DataResponse();
        dataResponse.setData(data);
        dataResponse.setCode(0);
        dataResponse.setMsg("操作成功");
        return dataResponse;
    }

    public static DataResponse Fail(String message){
        DataResponse dataResponse = new DataResponse();
        dataResponse.setCode(1);
        dataResponse.setMsg(message);
        return dataResponse;
    }
}
