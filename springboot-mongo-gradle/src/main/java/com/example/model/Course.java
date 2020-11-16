package com.example.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * 课程
 * @author zb
 * @date 2019-03-31 16:26
 **/
@Document
public class Course extends DeptDomain {
    /**
     * 课程编码
     */
    private String code;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 课程分类
     */
    @DBRef(lazy = true)
    private CourseCategory courseCategory;
    /**
     * 课程学分
     */
    private Integer credit;
    /**
     * 是否公开
     */
    private Boolean open;
    /**
     * 图片URL
     */
    private String cover;
    /**
     * 讲师
     */
    @DBRef(lazy = true)
    private Lecturer lecturer;
    /**
     * 答疑账户，用户ID
     */
    @DBRef(lazy = true)
    private User answerUser;
    /**
     * 课程介绍
     */
    private String introduce;
    /**
     * 完成标准，THE_COURSE(学完课程)、COMPLETE_ EXAM(完成考试)
     */
    private String completionCriteria;
    /**
     * 试卷信息
     */
    private List<ExamInfo> exams;
    /**
     * 课件数据
     */
    @DBRef(lazy = true)
    private List<Courseware> coursewares;
    /**
     * 平台课程ID
     */
    private String platformCourseId;
    /**
     * 是否收费。主要描述平台课程是否收费
     */
    private Boolean rechargeable;
    /**
     * 状态。主要描述平台课程的状态
     *      未接收(NOT_RECIVED), 已接收(RECIVED), 已分配(DISTRIBUTED)
     */
    private String status;
    /**
     * 是否删除
     */
    private Boolean delFlag = false;
    /**
     * 该部门所属的机构
     */
    @DBRef(lazy = true)
    private Dept company;
    /**
     * 备注
     */
    private String remark;

    @Override
    public String toString() {
        return "Course{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", courseCategory=" + courseCategory +
                ", credit=" + credit +
                ", open=" + open +
                ", cover='" + cover + '\'' +
                ", lecturer=" + lecturer +
                ", answerUser=" + answerUser +
                ", introduce='" + introduce + '\'' +
                ", completionCriteria='" + completionCriteria + '\'' +
                ", exams=" + exams +
                ", coursewares=" + coursewares +
                ", platformCourseId='" + platformCourseId + '\'' +
                ", rechargeable=" + rechargeable +
                ", status='" + status + '\'' +
                ", delFlag=" + delFlag +
                ", company=" + company +
                ", remark='" + remark + '\'' +
                '}';
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

    public CourseCategory getCourseCategory() {
        return courseCategory;
    }

    public void setCourseCategory(CourseCategory courseCategory) {
        this.courseCategory = courseCategory;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public User getAnswerUser() {
        return answerUser;
    }

    public void setAnswerUser(User answerUser) {
        this.answerUser = answerUser;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getCompletionCriteria() {
        return completionCriteria;
    }

    public void setCompletionCriteria(String completionCriteria) {
        this.completionCriteria = completionCriteria;
    }

    public List<ExamInfo> getExams() {
        return exams;
    }

    public void setExams(List<ExamInfo> exams) {
        this.exams = exams;
    }

    public List<Courseware> getCoursewares() {
        return coursewares;
    }

    public void setCoursewares(List<Courseware> coursewares) {
        this.coursewares = coursewares;
    }

    public String getPlatformCourseId() {
        return platformCourseId;
    }

    public void setPlatformCourseId(String platformCourseId) {
        this.platformCourseId = platformCourseId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getRechargeable() {
        return rechargeable;
    }

    public void setRechargeable(Boolean rechargeable) {
        this.rechargeable = rechargeable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public Dept getCompany() {
        return company;
    }

    public void setCompany(Dept company) {
        this.company = company;
    }
}
