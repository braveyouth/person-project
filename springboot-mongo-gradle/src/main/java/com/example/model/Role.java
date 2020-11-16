package com.example.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author zb
 * @date 2019-03-31 17:40
 **/
@Document
public class Role extends DomainId {

    /**
     * 角色名
     */
    private String name;
    /**
     * 角色显示名
     */
    private String displayName;
    /**
     * 备注
     */
    private String remark;

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
