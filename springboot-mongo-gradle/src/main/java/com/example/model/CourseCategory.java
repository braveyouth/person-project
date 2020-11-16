package com.example.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 课程分类
 * @author zb
 * @date 2019-04-01 15:06
 **/
@Document(value = "course_category")
public class CourseCategory extends DomainId {
    /**
     * 课程编码
     */
    private String code;
    /**
     * 课程分类名称
     */
    private String name;
    /**
     * 课程分类图片
     */
    private String cover;
    /**
     * 父级分类
     */
    @DBRef(lazy = true)
    private CourseCategory parent;

    @Override
    public String toString() {
        return "CourseCategory{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", cover='" + cover + '\'' +
                ", parent=" + parent +
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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public CourseCategory getParent() {
        return parent;
    }

    public void setParent(CourseCategory parent) {
        this.parent = parent;
    }

}
