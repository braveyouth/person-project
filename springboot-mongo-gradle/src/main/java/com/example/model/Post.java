package com.example.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 岗位
 * @author zb
 * @date 2019-03-31 17:02
 **/
@Document
public class Post extends DomainId {

    /**
     * 岗位编码
     */
    private String code;
    /**
     * 岗位名称
     */
    private String name;
    /**
     * 父级岗位
     */
    private String parentId;
    /**
     * 备注
     */
    private String remark;

    @Override
    public String toString() {
        return "Post{" +
                "code='" + code + '\'' +
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
