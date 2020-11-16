package com.example.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author zb
 * @date 2019-04-04 16:08
 **/
@Document
public class Task extends DomainId {

    private String userId;
    /**
     * 成长计划年份
     */
    private Integer year;
    /**
     * 成长计划ID
     */
    private String growthPlanId;
    /**
     * 培训班ID
     */
    private String trainingClassId;
    /**
     * 直播课程ID
     */
    private String liveCourseId;
    /**
     * 名称
     */
    private String name;
    /**
     * 图片
     */
    private String cover;
    /**
     * 属性名
     */
    private String property;
    /**
     * 属性值
     */
    private String value;
    /**
     * 进度条
     */
    private BigDecimal progress;
    /**
     * 截止时间
     */
    private Integer deadline;
    /**
     * 状态 完成情况，已完成(COMPLETED)、待完成(UNFINISHED)、已逾期(OVERDUE)
     */
    private String status;
    /**
     * 课程数据
     */
    private List<CourseInfo> courseInfos;
    /**
     * 考试
     */
    private List<ExamInfo> exams;

    @Override
    public String toString() {
        return "Task{" +
                "userId='" + userId + '\'' +
                ", year=" + year +
                ", growthPlanId='" + growthPlanId + '\'' +
                ", trainingClassId='" + trainingClassId + '\'' +
                ", liveCourseId='" + liveCourseId + '\'' +
                ", name='" + name + '\'' +
                ", cover='" + cover + '\'' +
                ", property='" + property + '\'' +
                ", value='" + value + '\'' +
                ", progress=" + progress +
                ", deadline=" + deadline +
                ", status='" + status + '\'' +
                ", courseInfos=" + courseInfos +
                ", exams=" + exams +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getGrowthPlanId() {
        return growthPlanId;
    }

    public void setGrowthPlanId(String growthPlanId) {
        this.growthPlanId = growthPlanId;
    }

    public String getTrainingClassId() {
        return trainingClassId;
    }

    public void setTrainingClassId(String trainingClassId) {
        this.trainingClassId = trainingClassId;
    }

    public String getLiveCourseId() {
        return liveCourseId;
    }

    public void setLiveCourseId(String liveCourseId) {
        this.liveCourseId = liveCourseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BigDecimal getProgress() {
        return progress;
    }

    public void setProgress(BigDecimal progress) {
        this.progress = progress;
    }

    public Integer getDeadline() {
        return deadline;
    }

    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CourseInfo> getCourseInfos() {
        return courseInfos;
    }

    public void setCourseInfos(List<CourseInfo> courseInfos) {
        this.courseInfos = courseInfos;
    }

    public List<ExamInfo> getExams() {
        return exams;
    }

    public void setExams(List<ExamInfo> exams) {
        this.exams = exams;
    }
}
