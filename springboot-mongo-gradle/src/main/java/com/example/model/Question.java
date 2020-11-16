package com.example.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

/**
 * 试题
 * @author zb
 * @date 2019-03-31 14:20
 **/
@Document(value = "question")
public class Question extends DomainId {

    /**
     * 类型：SINGLE_CHOICE(单选题)、MULTIPLE_CHOICE(多选题)、JUDGE_CHOICE(判断题)
     */
    private String type;
    /**
     * 试题难度：SIMPLENESS(简单)、MEDIUM(中等)、DIFFICULTY(困难)
     */
    private String difficultyLevel;
    /**
     * 试题分类
     */
    @DBRef
    private QuestionPaperCategory category;
    /**
     * 试题内容
     */
    private String content;
    /**
     * 选项： 键值{key:value}，例如：[{'label':a,'value':false}]
     */
    private List<Map<String, String>> options;
    /**
     * 答案：选项key，例如：[A]
     */
    private List<String> answers;
    /**
     * 试题分析
     */
    private String analysis;
    /**
     * 状态，启用禁用
     */
    private boolean status;

    @Override
    public String toString() {
        return "Question{" +
                "type='" + type + '\'' +
                ", difficultyLevel='" + difficultyLevel + '\'' +
                ", category=" + category +
                ", content='" + content + '\'' +
                ", options=" + options +
                ", answers=" + answers +
                ", analysis='" + analysis + '\'' +
                ", status='" + status + '\'' +
                '}';
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

    public QuestionPaperCategory getCategory() {
        return category;
    }

    public void setCategory(QuestionPaperCategory category) {
        this.category = category;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Map<String, String>> getOptions() {
        return options;
    }

    public void setOptions(List<Map<String, String>> options) {
        this.options = options;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
