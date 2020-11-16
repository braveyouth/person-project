package com.example.controller;

import com.example.common.*;
import com.example.common.request.Req1001;
import com.example.common.request.Req1002;
import com.example.common.response.Res1001;
import com.example.common.response.Res1002;
import com.example.service.PersonMapperService;
import com.github.pagehelper.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author created by zhangyong
 * @Time 2020/3/2 14:04
 */
@Slf4j
@RestController
@RequestMapping("/personmapper")
public class PersonMapperController {

    @Autowired
    private PersonMapperService personMapperService;

    /**
     * 获取赠险
     * @param req1001
     * @param errors
     * @return
     */
    @RequestMapping(value = "/get",method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse getController(@RequestBody @Valid Req1001 req1001, Errors errors) throws Exception {

        if(errors.hasErrors()){
            return BaseResponse.fail(errors.getFieldError().getDefaultMessage());
        }

        //校验请求参数
        final BoolString check = personMapperService.checkReq1001(req1001);
        if(!check.isOk()){
            return BaseResponse.fail(check.getErrMsg());
        }

        personMapperService.getService(req1001);


        return BaseResponse.Ok("领取成功");
    }

    /**
     * 查询
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/1001",method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ApiResponse<?> selectController(@RequestBody Req1002 req1002) throws Exception {

        Page<Res1001> page = personMapperService.selectService(req1002);
        Res1002 res1002 = new Res1002();
        res1002.setPages(page.getPages());
        res1002.setTotal(page.getTotal());
        res1002.setList(page.getResult());



        return new ApiResponse<>(ApiMessage.OK,res1002);
    }

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/1002",method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse exportController(@RequestBody Req1002 req1002) throws Exception {

        personMapperService.exportService(req1002);

        return BaseResponse.Ok("导出成功");
    }

    /**
     * 已领取份数
     * @return
     */
    @RequestMapping(value = "/1003",method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public DataResponse sumController(){
        int i = personMapperService.sumService();
        return DataResponse.Ok(i);
    }
}
