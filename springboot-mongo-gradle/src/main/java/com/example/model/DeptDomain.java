package com.example.model;


/**
 * 继承该类后dept字段唯一
 * @author zb
 * @date 2019-04-02 14:10
 **/
public class DeptDomain extends DomainId {

    /**
     * 部门ID
     */
    private String deptId;

    @Override
    public String toString() {
        return "DeptDomain{" +
                "deptId='" + deptId + '\'' +
                '}';
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}
