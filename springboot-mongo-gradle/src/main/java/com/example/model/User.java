package com.example.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 员工与用户
 * @author zb
 * @date 2019-03-31 17:04
 **/
@Document
public class User extends DomainId {

    /**
     * 用户名
     */
    @Indexed(unique = true)
    private String username;
    /**
     * 中文名
     */
    private String name;
    /**
     * 员工工号
     */
    private String employeeId;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 员工手机号
     */
    private String mobile;
    /**
     * 用户身份证号码
     */
    private String identityCardNo;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 性别：FEMALE(女性)、MALE(男性)、SECRECY(保密)
     */
    private String gender;
    /**
     * 入职时间
     */
    private LocalDate entryTime;
    /**
     * 辞职时间
     */
    private LocalDate resignationTime;
    /**
     * 开通时间
     */
    private LocalDateTime openingTime = LocalDateTime.now();
    /**
     * 学历
     */
    private String degree;
    /**
     * 工作性质： INDOOR_STAFF(内勤)、LEGWORK(外勤)
     */
    private String workProperty;
    /**
     * 渠道
     */
    @DBRef(lazy = true)
    private Channel channel;
    /**
     * 所属部门ID
     */
    @DBRef(lazy = true)
    private Dept dept;
    /**
     * 所属岗位
     */
    @DBRef(lazy = true)
    private List<Post> posts;
    /**
     * 职级
     */
    @DBRef(lazy = true)
    private Rank rank;
    /**
     * 系列
     */
    @DBRef(lazy = true)
    private Series series;
    /**
     * 标记是否删除
     */
    private Boolean delFlag = false;
    /**
     * 授权用户ID
     */
    private String oauthUserId;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", identityCardNo='" + identityCardNo + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", entryTime=" + entryTime +
                ", resignationTime=" + resignationTime +
                ", openingTime=" + openingTime +
                ", degree='" + degree + '\'' +
                ", workProperty='" + workProperty + '\'' +
                ", dept=" + dept +
                ", posts=" + posts +
                ", rank=" + rank +
                ", series=" + series +
                ", delFlag=" + delFlag +
                ", oauthUserId='" + oauthUserId + '\'' +
                '}';
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

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdentityCardNo() {
        return identityCardNo;
    }

    public void setIdentityCardNo(String identityCardNo) {
        this.identityCardNo = identityCardNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDate entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDate getResignationTime() {
        return resignationTime;
    }

    public void setResignationTime(LocalDate resignationTime) {
        this.resignationTime = resignationTime;
    }

    public LocalDateTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalDateTime openingTime) {
        this.openingTime = openingTime;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getWorkProperty() {
        return workProperty;
    }

    public void setWorkProperty(String workProperty) {
        this.workProperty = workProperty;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String getOauthUserId() {
        return oauthUserId;
    }

    public void setOauthUserId(String oauthUserId) {
        this.oauthUserId = oauthUserId;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
