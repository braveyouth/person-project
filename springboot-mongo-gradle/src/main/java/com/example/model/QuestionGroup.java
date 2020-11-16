package com.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author zb
 * @date 2019-03-31 14:39
 **/
@Document("question_group")
public class QuestionGroup {

    @Id
    private String id;
    /**
     * 组卷类型：SINGLE_CHOICE(单选题)、MULTIPLE_CHOICE(多选题)、JUDGE_CHOICE(判断题)
     */
    private String type;
    /**
     * 组卷标题
     */
    private String title;
    /**
     * 每一题的分数
     */
    private Integer scorePerQuestion;
    /**
     * 试题数据
     */
    @DBRef
    private List<Question> questions;
    /**
     * 备注
     */
    private String remark;

    @Override
    public String toString() {
        return "QuestionGroup{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", scorePerQuestion=" + scorePerQuestion +
                ", questions=" + questions +
                ", remark='" + remark + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getScorePerQuestion() {
        return scorePerQuestion;
    }

    public void setScorePerQuestion(Integer scorePerQuestion) {
        this.scorePerQuestion = scorePerQuestion;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
