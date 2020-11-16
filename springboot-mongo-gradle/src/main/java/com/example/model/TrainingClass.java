package com.example.model;


import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

/**
 * 培训班
 * @author zb
 * @date 2019-04-01 15:33
 **/
@Document(value = "training_class")
public class TrainingClass extends DeptDomain {

    /**
     * 培训班名称
     */
    private String name;
    /**
     * 培训班介绍
     */
    private String introduce;
    /**
     * 图片地址
     */
    private String cover;
    /**
     * 类型，例如：ON_LINE(线上)，OFF_LINE(线下)
     */
    private String type;
    /**
     * 状态 NOT_RELEASE(未发布), RELEASE(已发布), OVER(已结束)
     */
    private String status;
    /**
     * 开始时间
     */
    private LocalDate startTime;
    /**
     * 结束时间
     */
    private LocalDate endTime;
    /**
     * 学分
     */
    private Integer credit;
    /**
     * 是否报名
     */
    private Boolean enroll;
    /**
     * 培训班课程
     */
    private List<CourseInfo> courses;
    /**
     * 培训班试卷
     */
    private List<ExamInfo> exams;
    /**
     * 特定学习人员
     */
    @DBRef(lazy = true)
    private List<User> users;

    @Override
    public String toString() {
        return "TrainingClass{" +
                "name='" + name + '\'' +
                ", introduce='" + introduce + '\'' +
                ", cover='" + cover + '\'' +
                ", type='" + type + '\'' +
                ", status=" + status +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", credit=" + credit +
                ", enroll=" + enroll +
                ", courses=" + courses +
                ", exams=" + exams +
                ", users=" + users +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Boolean getEnroll() {
        return enroll;
    }

    public void setEnroll(Boolean enroll) {
        this.enroll = enroll;
    }

    public List<CourseInfo> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseInfo> courses) {
        this.courses = courses;
    }

    public List<ExamInfo> getExams() {
        return exams;
    }

    public void setExams(List<ExamInfo> exams) {
        this.exams = exams;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
