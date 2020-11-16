package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * 企业
 * @author zb
 * @date 2019-03-31 16:14
 **/
@Document
public class Org {

    @Id
    private String id;
    /**
     * 组织编码
     */
    private String code;
    /**
     * 组织名称
     */
    private String name;
    /**
     * 接入时间
     */
    private LocalDateTime accessTime;
    /**
     * 合同开始时间
     */
    private LocalDateTime contractStartTime;
    /**
     * 合同结束时间
     */
    private LocalDateTime contractEndTime;
    /**
     * 企业管理员
     */
    private String adminName;
    /**
     * 企业管理员账户
     */
    private String adminUsername;
    /**
     * 联系人
     */
    private String contactName;
    /**
     * 联系人手机号
     */
    private String contactPhone;
    /**
     * 企业状态，禁用或启用
     */
    private String status;
    /**
     *
     */
    private String remark;
    /**
     * 组织域
     */
    private String domain;
    /**
     * 创建者
     */
    private String creator;
    /**
     * 创建时间
     */
    private LocalDateTime createTime = LocalDateTime.now();
    /**
     * 最后一次操作者
     */
    private String lastModifier;
    /**
     * 最后一次操作时间
     */
    private LocalDateTime lastModifyTime;

    @Override
    public String toString() {
        return "Org{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", accessTime=" + accessTime +
                ", contractStartTime=" + contractStartTime +
                ", contractEndTime=" + contractEndTime +
                ", adminName='" + adminName + '\'' +
                ", adminUsername='" + adminUsername + '\'' +
                ", contactName='" + contactName + '\'' +
                ", contactPhone='" + contactPhone + '\'' +
                ", status=" + status +
                ", remark='" + remark + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", domain='" + domain + '\'' +
                ", lastModifier='" + lastModifier + '\'' +
                ", lastModifyTime='" + lastModifyTime + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public LocalDateTime getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(LocalDateTime accessTime) {
        this.accessTime = accessTime;
    }

    public LocalDateTime getContractStartTime() {
        return contractStartTime;
    }

    public void setContractStartTime(LocalDateTime contractStartTime) {
        this.contractStartTime = contractStartTime;
    }

    public LocalDateTime getContractEndTime() {
        return contractEndTime;
    }

    public void setContractEndTime(LocalDateTime contractEndTime) {
        this.contractEndTime = contractEndTime;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getLastModifier() {
        return lastModifier;
    }

    public void setLastModifier(String lastModifier) {
        this.lastModifier = lastModifier;
    }

    public LocalDateTime getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(LocalDateTime lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}
