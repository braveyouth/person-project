package com.example.common.model.dto;

import com.example.model.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.BeanUtils;

/**
 * @author zhangy
 * @Time 2020-11-11 22:42
 */
@ApiModel("用户返回参数")
public class UserDto {
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("中文名")
    private String name;
    @ApiModelProperty("员工手机号")
    private String mobile;
    @ApiModelProperty("用户邮箱")
    private String email;
    @ApiModelProperty("性别：FEMALE(女性)、MALE(男性)、SECRECY(保密)")
    private String gender;

    public static UserDto of(User user){
        if(user==null){
            return null;
        }
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
