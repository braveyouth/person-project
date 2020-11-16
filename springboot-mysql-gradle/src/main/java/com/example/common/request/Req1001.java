package com.example.common.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author created by zhangyong
 * @Time 2020/3/2 15:23
 */
@Data
public class Req1001 {

    @NotBlank(message = "姓名不能为空")
    private String name;

    @NotBlank(message = "身份证号不能为空")
    private String idCard;

    @NotBlank(message = "手机号不能为空")
    @Length(max = 11,min = 11,message = "手机号长度不正确")
//    @Pattern(regexp = "^(13[0-9]|15[0-9]|18[0-9]|17[0-9])\\d{8}$",message = "手机号有误")
    private String phone;

    @NotBlank(message = "银行卡号不能为空")
//    @Pattern(regexp = "^\\d{16}$",message = "银行卡号错误")
    private String bankCard;

    @NotBlank(message = "邮箱不能为空")
    private String email;

    @NotBlank(message = "问题一不能选择为空")
    private String queOne;

    @NotBlank(message = "问题二不能选择为空")
    private String queTwo;

    @NotBlank(message = "问题三不能选择为空")
    private String queThree;

    @NotBlank(message = "问题四不能选择为空")
    private String queFour;

    @NotBlank(message = "问题五不能选择为空")
    private String queFive;

    private String time;
}
