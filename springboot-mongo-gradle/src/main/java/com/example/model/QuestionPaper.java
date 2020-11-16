package com.example.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * 试卷
 * @author zb
 * @date 2019-03-31 14:33
 **/
@Document("question_paper")
public class QuestionPaper extends DomainId {

    /**
     * 试卷名称
     */
    private String name;
    /**
     * 试卷分类ID
     */
    @DBRef
    private QuestionPaperCategory category;
    /**
     * 类型，RANDOM_QUESTION(随机题)、ORDER_QUESTION(顺序题)
     */
    private String type;
    /**
     * 难度：SIMPLENESS(简单)、MEDIUM(中等)、DIFFICULTY(困难)
     */
    private String difficultyLevel;
    /**
     * 分数
     */
    private Integer score;
    /**
     * 考试时长
     */
    private Integer timeLimit;
    /**
     * 分数线
     */
    private Integer passLine;
    /**
     * 组卷集
     */
    @DBRef
    private List<QuestionGroup> questionItems;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态
     */
    private Boolean status;

    @Override
    public String toString() {
        return "QuestionPaper{" +
                "name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", difficultyLevel='" + difficultyLevel + '\'' +
                ", score=" + score +
                ", timeLimit=" + timeLimit +
                ", passLine=" + passLine +
                ", questionItems=" + questionItems +
                ", remark='" + remark + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public QuestionPaperCategory getCategory() {
        return category;
    }

    public void setCategory(QuestionPaperCategory category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getPassLine() {
        return passLine;
    }

    public void setPassLine(Integer passLine) {
        this.passLine = passLine;
    }

    public List<QuestionGroup> getQuestionItems() {
        return questionItems;
    }

    public void setQuestionItems(List<QuestionGroup> questionItems) {
        this.questionItems = questionItems;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
