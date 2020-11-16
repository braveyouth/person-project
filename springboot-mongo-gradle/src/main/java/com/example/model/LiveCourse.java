package com.example.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 直播课程
 * @author zb
 * @date 2019-04-01 18:22
 **/
@Document(value = "live_course")
public class LiveCourse extends DeptDomain {
    /**
     * 直播课程
     */
    private String name;
    /**
     * 课程分类
     */
    @DBRef(lazy = true)
    private CourseCategory courseCategory;
    /**
     * 排序序号。数字越大排越前面
     */
    private Integer sortNo;
    /**
     * 学分
     */
    private Integer credit;
    /**
     * 直播房间号
     */
    private Long roomNum;
    /**
     * 图片URL
     */
    private String cover;
    /**
     * 是否公开
     */
    private Boolean open;
    /**
     * 直播开始时间
     */
    private LocalDateTime startTime;
    /**
     * 直播结束时间
     */
    private LocalDateTime endTime;
    /**
     * 直播课程介绍
     */
    private String introduce;
    /**
     * 讲师
     */
    @DBRef(lazy = true)
    private Lecturer lecturer;
    /**
     * 学习用户
     */
    @DBRef(lazy = true)
    private List<User> users;
    /**
     * 是否发布
     */
    private Boolean release = false;
    /**
     * 直播状态。正在直播(0), 暂停中(1), 即将直播(2)，未开始(3)
     * 已结束(4)，审核不通过(5)
     */
    private String status;
    /**
     * 标记是否删除
     */
    private Boolean delFlag = false;

    @Override
    public String toString() {
        return "LiveCourse{" +
                "name='" + name + '\'' +
                ", courseCategory=" + courseCategory +
                ", sortNo=" + sortNo +
                ", credit=" + credit +
                ", roomNum=" + roomNum +
                ", cover='" + cover + '\'' +
                ", open=" + open +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", introduce='" + introduce + '\'' +
                ", lecturer=" + lecturer +
                ", users=" + users +
                ", release=" + release +
                ", status='" + status + '\'' +
                ", delFlag=" + delFlag +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
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

    public Long getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(Long roomNum) {
        this.roomNum = roomNum;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Boolean getRelease() {
        return release;
    }

    public void setRelease(Boolean release) {
        this.release = release;
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
}
