package com.example.model;

/**
 * @author zb
 * @date 2019-04-01 18:58
 **/
public class Exams {

    /**
     * 用户信息
     */
    private String id;
    private String name;
    private String difficultyLevel;
    private int count;
    private int sortNo;
    /**
     * 是否完成考试
     */
    private boolean done = false;

    @Override
    public String toString() {
        return "Exams{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", difficultyLevel='" + difficultyLevel + '\'' +
                ", count=" + count +
                ", sortNo=" + sortNo +
                ", done=" + done +
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

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
