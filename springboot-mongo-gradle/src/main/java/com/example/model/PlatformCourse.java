package com.example.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author zb
 * @date 2019-03-31 17:42
 **/
@Document(value = "platform_course")
public class PlatformCourse extends DomainId {
    /**
     * 名称
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
     * 是否收费
     */
    private Boolean rechargeable;
    /**
     * 收费金额
     */
    private Double charge;
    /**
     * 图片URL
     */
    private String cover;
    /**
     * 课程介绍
     */
    private String introduce;
    /**
     * 讲师
     */
    @DBRef(lazy = true)
    private Lecturer lecturer;
    /**
     * 答疑账号,用户
     */
    @DBRef(lazy = true)
    private User answerUser;
    /**
     * 课件数组
     */
    @DBRef(lazy = true)
    private List<Courseware> coursewares;
    /**
     * 点赞数
     */
    private Integer likedNum;

    @Override
    public String toString() {
        return "PlatformCourse{" +
                "name='" + name + '\'' +
                ", courseCategory=" + courseCategory +
                ", credit=" + credit +
                ", rechargeable=" + rechargeable +
                ", charge=" + charge +
                ", cover='" + cover + '\'' +
                ", introduce='" + introduce + '\'' +
                ", lecturer=" + lecturer +
                ", answerUser=" + answerUser +
                ", coursewares=" + coursewares +
                ", likedNum=" + likedNum +
                '}';
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

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public User getAnswerUser() {
        return answerUser;
    }

    public void setAnswerUser(User answerUser) {
        this.answerUser = answerUser;
    }

    public List<Courseware> getCoursewares() {
        return coursewares;
    }

    public void setCoursewares(List<Courseware> coursewares) {
        this.coursewares = coursewares;
    }

    public Boolean getRechargeable() {
        return rechargeable;
    }

    public void setRechargeable(Boolean rechargeable) {
        this.rechargeable = rechargeable;
    }

    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) {
        this.charge = charge;
    }

    public Integer getLikedNum() {
        return likedNum;
    }

    public void setLikedNum(Integer likedNum) {
        this.likedNum = likedNum;
    }
}
