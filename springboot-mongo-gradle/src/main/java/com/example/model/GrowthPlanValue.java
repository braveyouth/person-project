package com.example.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author hqq
 * @date 2019-04-25 9:52
 **/
@Document("growth_plan_value")
public class GrowthPlanValue extends DomainId {
    /**
     * 名称
     */
    private String name;
    /**
     * 编码
     */
    private String code;
    /**
     * 属性ID
     */
    private String propertyId;
    /**
     * 状态。是否可用
     */
    private Boolean enable;
    /**
     * 备注
     */
    private String remark;

    @Override
    public String toString() {
        return "GrowthPlanValue{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", propertyId='" + propertyId + '\'' +
                ", enable=" + enable +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
