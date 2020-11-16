package com.example.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author zb
 * @date 2019-04-01 16:02
 **/
@Document
public class Lecturer extends DeptDomain {

    /**
     * 讲师名称
     */
    private String name;
    /**
     * 头像URL
     */
    private String headUrl;
    /**
     * 讲师手机号
     */
    private String mobile;
    /**
     * 讲师级别
     */
    private String level;
    /**
     * 讲师资历
     */
    private String senior;
    /**
     * 讲师介绍
     */
    private String introduce;
    /**
     * 是否是外部讲师
     */
    private Boolean external;
    /**
     * 讲师直播账号
     */
    @DBRef(lazy = true)
    private LiveAccount liveAccount;

    @Override
    public String toString() {
        return "Lecturer{" +
                "name='" + name + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", mobile='" + mobile + '\'' +
                ", level='" + level + '\'' +
                ", senior='" + senior + '\'' +
                ", introduce='" + introduce + '\'' +
                ", external=" + external +
                ", liveAccount=" + liveAccount +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSenior() {
        return senior;
    }

    public void setSenior(String senior) {
        this.senior = senior;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Boolean getExternal() {
        return external;
    }

    public void setExternal(Boolean external) {
        this.external = external;
    }

    public LiveAccount getLiveAccount() {
        return liveAccount;
    }

    public void setLiveAccount(LiveAccount liveAccount) {
        this.liveAccount = liveAccount;
    }
}
