package com.example.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 系列
 * @author zb
 * @date 2019-04-01 15:13
 **/
@Document
public class Series extends DomainId {

    /**
     * 系列编码
     */
    private String code;
    /**
     * 系列名称
     */
    private String name;
    /**
     * 备注
     */
    private String remark;

    @Override
    public String toString() {
        return "Series{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
