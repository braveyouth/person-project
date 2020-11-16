package com.example.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author zb
 * @date 2019-04-18 17:58
 **/
@Document(value = "study_status")
public class StudyStatus extends DomainId {

    /**
     * 用户信息
     */
    private String userId;
    /**
     * 课程信息
     */
    private String courseId;
    /**
     * 平台课程ID
     */
    private String platformCourseId;
    /**
     * 课件ID
     */
    private String coursewareId;
    /**
     * 课件的学习状态
     */
    private Map<String, Boolean> coursewareDones;
    /**
     * 考试的学习状态
     */
    private Map<String, ExamRecord> examDones;
    /**
     * 视频总时长
     */
    private Integer totalTime = 0;
    /**
     * 课程是否学习完成
     */
    private Boolean done = false;
    /**
     * 最近学习的时间点
     */
    private Integer lastStudyDuration = 0;
    /**
     * 课程(COURSE)、课件(COURSE_WARE)、平台课程(PLATFORM_COURSE)、平台课程课件(PLATFORM_COURSE_WARE)
     */
    private String type;
    /**
     * 完成时间
     */
    private LocalDateTime doneTime;

    @Override
    public String toString() {
        return "StudyStatus{" +
                "userId='" + userId + '\'' +
                ", courseId='" + courseId + '\'' +
                ", platformCourseId='" + platformCourseId + '\'' +
                ", coursewareId='" + coursewareId + '\'' +
                ", coursewareDones=" + coursewareDones +
                ", examDones=" + examDones +
                ", totalTime=" + totalTime +
                ", done=" + done +
                ", lastStudyDuration=" + lastStudyDuration +
                ", type='" + type + '\'' +
                ", doneTime=" + doneTime +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCoursewareId() {
        return coursewareId;
    }

    public String getPlatformCourseId() {
        return platformCourseId;
    }

    public void setPlatformCourseId(String platformCourseId) {
        this.platformCourseId = platformCourseId;
    }

    public void setCoursewareId(String coursewareId) {
        this.coursewareId = coursewareId;
    }

    public Map<String, Boolean> getCoursewareDones() {
        return coursewareDones;
    }

    public void setCoursewareDones(Map<String, Boolean> coursewareDones) {
        this.coursewareDones = coursewareDones;
    }

    public Map<String, ExamRecord> getExamDones() {
        return examDones;
    }

    public void setExamDones(Map<String, ExamRecord> examDones) {
        this.examDones = examDones;
    }

    public Integer getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Integer totalTime) {
        this.totalTime = totalTime;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Integer getLastStudyDuration() {
        return lastStudyDuration;
    }

    public void setLastStudyDuration(Integer lastStudyDuration) {
        this.lastStudyDuration = lastStudyDuration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(LocalDateTime doneTime) {
        this.doneTime = doneTime;
    }
}
