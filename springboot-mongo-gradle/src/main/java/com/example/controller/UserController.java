package com.example.controller;

import com.example.api.UserControllerApi;
import com.example.common.model.ApiMessage;
import com.example.common.model.dto.UserDto;
import com.example.common.model.response.ApiResponse;
import com.example.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangy
 * @Time 2020/4/3 23:49
 */
@RestController
@Api("用户管理")
@RequestMapping("/user")
public class UserController implements UserControllerApi {

    @Autowired
    private IUserService userService;

    @Override
    @ApiOperation(value = "用户列表",response = UserDto.class)
    @ApiImplicitParam(name = "pageable",value = "分页参数",paramType = "query",required = true)
    @RequestMapping(value = "/1001",method = RequestMethod.GET)
    public ApiResponse<?> findUserListController(@PageableDefault Pageable pageable) {
        return new ApiResponse<>(ApiMessage.OK,userService.findUserListService(pageable));
    }
}
