package com.example.controller;

import com.example.common.model.ApiMessage;
import com.example.common.model.response.ApiResponse;
import com.whjrjf.check.RegularCheck;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangy
 * @Time 2020/4/3 23:26
 */
@RestController
@RequestMapping("/test")
public class TestController {


    @RequestMapping("/hello")
    public String sayHello(){
        return "Hello";
    }

    @RequestMapping("tool")
    public ApiResponse<?> tool(){
        return new ApiResponse<>(ApiMessage.OK, RegularCheck.isMobile("138715467501"));
    }

}
