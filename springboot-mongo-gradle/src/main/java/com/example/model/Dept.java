package com.example.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 部门
 * @author zb
 * @date 2019-03-31 16:59
 **/
@Document
public class Dept extends DomainId {

    /**
     * 机构编码
     */
    private String code;
    /**
     * 类型(公司或部门)
     */
    private String type;
    /**
     * 名称
     */
    private String name;
    /**
     * 父级部门ID
     */
    private String parentId;
    /**
     * 备注
     */
    private String remark;

    @Override
    public String toString() {
        return "Dept{" +
                "code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
