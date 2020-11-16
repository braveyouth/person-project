package com.example.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author hqq
 * @date 2019-04-25 9:52
 **/
@Document("growth_plan_property")
public class GrowthPlanProperty extends DomainId {
    /**
     * 属性名称
     */
    private String name;
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
        return "GrowthPlanProperty{" +
                "name='" + name + '\'' +
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
