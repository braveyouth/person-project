package com.example.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * 成长计划
 * @author zb
 * @date 2019-04-01 15:24
 **/
@Document(value = "growth_plan")
public class GrowthPlan extends DeptDomain {
    /**
     * 成长计划年份
     */
    private Integer year;
    /**
     * 成长计划名称
     */
    private String name;
    /**
     * 成长计划介绍
     */
    private String introduce;
    /**
     * 成长计划图片
     */
    private String cover;
    /**
     * 属性
     */
    private String property;
    /**
     * 值
     */
    private String value;
    /**
     * 课程
     */
    private List<CourseInfo> courses;
    /**
     * 考试
     */
    private List<ExamInfo> exams;

    @Override
    public String toString() {
        return "GrowthPlan{" +
                ", year=" + year +
                ", name='" + name + '\'' +
                ", introduce='" + introduce + '\'' +
                ", cover='" + cover + '\'' +
                ", property='" + property + '\'' +
                ", value='" + value + '\'' +
                ", courses=" + courses +
                ", exams=" + exams +
                '}';
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
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
}
