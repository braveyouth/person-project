package com.example.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author zb
 * @date 2019-07-05 10:03
 **/
@Document(value = "user_login_log")
public class UserLoginLog extends DomainId {

    /**
     * 第三方登录id
     */
    private String thirdId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 登录时间
     */
    private LocalDateTime loginTime;
    /**
     * 登录设备信息
     */
    private String userAgent;
    /**
     * 登录IP地址
     */
    private String ip;

    @Override
    public String toString() {
        return "UserLoginLog{" +
                "thirdId='" + thirdId + '\'' +
                ", username='" + username + '\'' +
                ", loginTime='" + loginTime + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }

    public String getThirdId() {
        return thirdId;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
