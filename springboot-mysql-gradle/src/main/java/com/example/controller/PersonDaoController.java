package com.example.controller;

import com.example.common.request.Req1001;
import com.example.common.response.Res1001;
import com.example.common.BaseResponse;
import com.example.common.BoolString;
import com.example.common.DataResponse;
import com.example.service.PersonDaoService;
import lombok.extern.slf4j.Slf4j;
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
//@RequestMapping("/persondao")
@RequestMapping("/person")
public class PersonDaoController {

    @Autowired
    private PersonDaoService personDaoService;

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
        final BoolString check = personDaoService.checkReq1001(req1001);
        if(!check.isOk()){
            return BaseResponse.fail(check.getErrMsg());
        }

        personDaoService.getService(req1001);

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
    public DataResponse selectController() throws Exception {

        List<Res1001> list = personDaoService.selectService();

        return DataResponse.Ok(list);
    }

    /**
     * 导出
     * @return
     */
    @RequestMapping(value = "/1002",method = RequestMethod.POST,
    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse exportController() throws Exception {

        personDaoService.exportService();

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
        int i = personDaoService.sumService();
        return DataResponse.Ok(i);
    }
}
