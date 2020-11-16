package com.example.model;

import com.example.util.SecurityUtil;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * HR管理员
 * @author zb
 * @date 2019-04-02 16:39
 **/
@Document(value = "hr_admin")
public class HrAdmin implements Serializable {
    @Id
    private String id;
    /**
     * 企业ID
     */
    private String orgId = SecurityUtil.getOrgId();
    /**
     * 组织域
     */
    private String domain = SecurityUtil.getDomain();
    /**
     * 公司/部门ID
     */
    private String deptId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 密码
     */
    private String password;
    /**
     * 系统自动生成盐值
     */
    private String salt;
    /**
     * 角色
     */
    @DBRef(lazy = true)
    private List<Role> roles;
    /**
     * 是否有效
     */
    private Boolean valid;
    /**
     * 创建者
     */
    private String creator = SecurityUtil.getUsername();
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
        if (!(o instanceof HrAdmin)) {
            return false;
        }
        HrAdmin hrAdmin = (HrAdmin) o;
        return Objects.equals(id, hrAdmin.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "HrAdmin{" +
                "id='" + id + '\'' +
                ", orgId='" + orgId + '\'' +
                ", domain='" + domain + '\'' +
                ", deptId='" + deptId + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", roles=" + roles +
                ", valid=" + valid +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", lastModifier='" + lastModifier + '\'' +
                ", lastModifyTime=" + lastModifyTime +
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

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
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
