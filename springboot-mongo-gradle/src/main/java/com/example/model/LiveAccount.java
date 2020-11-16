package com.example.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 直播账户
 * @author zb
 * @date 2019-04-01 16:07
 **/
@Document(value = "live_account")
public class LiveAccount extends DomainId {
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 直播账号
     */
    private String account;
    /**
     * 直播账号密码
     */
    private String password;
    /**
     * 直播状态
     */
    private String status;
    /**
     * 直播房间号
     */
    private String roomNum;
    /**
     * 备注
     */
    private String remark;

    @Override
    public String toString() {
        return "LiveAccount{" +
                "mobile='" + mobile + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", roomNum='" + roomNum + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
