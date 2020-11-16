package com.example.model;

/**
 * @author hqq
 * @date 2019-04-06 11:05
 **/
public class CourseInfos implements Comparable<CourseInfos> {
    /**
     * 课程ID
     */
    private String id;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 学分
     */
    private Integer credit;
    /**
     * 是否含考试
     */
    private Boolean hasExam;
    /**
     * 课程分类名称
     */
    private String categoryName;
    /**
     * 类型。  选修(Electives)/必修(Required)
     */
    private String type;
    /**
     * 序号
     */
    private Integer sortNo;

    @Override
    public int compareTo(CourseInfos o) {
        return this.sortNo - o.getSortNo();
    }

    @Override
    public String toString() {
        return "CourseInfos{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", credit=" + credit +
                ", hasExam=" + hasExam +
                ", categoryName='" + categoryName + '\'' +
                ", type='" + type + '\'' +
                ", sortNo=" + sortNo +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Boolean getHasExam() {
        return hasExam;
    }

    public void setHasExam(Boolean hasExam) {
        this.hasExam = hasExam;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

}
