package com.example.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author zb
 * @date 2019-03-31 16:21
 **/
public class DomainId implements Serializable {

    @Id
    private String id;
    /**
     * 组织机构
     */
    private String orgId;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DomainId)) {
            return false;
        }
        DomainId domainId = (DomainId) o;
        return Objects.equals(id, domainId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DomainId{" +
                "id='" + id + '\'' +
                ", orgId='" + orgId + '\'' +
                ", domain='" + domain + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
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

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
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
