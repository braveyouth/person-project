package com.example.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author zb
 * @date 2019-04-01 18:29
 **/
@Document(value = "study_record")
public class StudyRecord extends DomainId {

    /**
     * 用户信息
     */
    @DBRef(lazy = true)
    private User user;
    /**
     * 岗位信息
     */
    @DBRef(lazy = true)
    private List<Post> posts;
    /**
     * 课程信息
     */
    @DBRef(lazy = true)
    private Course course;
    /**
     * 课件序号
     */
    private Integer coursewareNo;
    /**
     * 开始学习时间
     */
    private LocalDateTime studyStartTime;
    /**
     * 结束学习时间
     */
    private LocalDateTime studyEndTime;
    /**
     * 视频持续时长
     */
    private Integer studyDuration;
    /**
     * 是否学习完成
     */
    private Boolean done = false;
    /**
     * 学分
     */
    private Integer credit;
    /**
     * 最近学习的时间点
     */
    private Integer lastStudyPoint;
    /**
     * 分两类，分别记录课程和课件
     */
    private String type;

    @Override
    public String toString() {
        return "StudyRecord{" +
                "user=" + user +
                ", posts=" + posts +
                ", course=" + course +
                ", coursewareNo=" + coursewareNo +
                ", studyStartTime=" + studyStartTime +
                ", studyEndTime=" + studyEndTime +
                ", studyDuration=" + studyDuration +
                ", done=" + done +
                ", credit=" + credit +
                ", lastStudyPoint=" + lastStudyPoint +
                ", type='" + type + '\'' +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getCoursewareNo() {
        return coursewareNo;
    }

    public void setCoursewareNo(Integer coursewareNo) {
        this.coursewareNo = coursewareNo;
    }

    public LocalDateTime getStudyStartTime() {
        return studyStartTime;
    }

    public void setStudyStartTime(LocalDateTime studyStartTime) {
        this.studyStartTime = studyStartTime;
    }

    public LocalDateTime getStudyEndTime() {
        return studyEndTime;
    }

    public void setStudyEndTime(LocalDateTime studyEndTime) {
        this.studyEndTime = studyEndTime;
    }

    public Integer getStudyDuration() {
        return studyDuration;
    }

    public void setStudyDuration(Integer studyDuration) {
        this.studyDuration = studyDuration;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getLastStudyPoint() {
        return lastStudyPoint;
    }

    public void setLastStudyPoint(Integer lastStudyPoint) {
        this.lastStudyPoint = lastStudyPoint;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
