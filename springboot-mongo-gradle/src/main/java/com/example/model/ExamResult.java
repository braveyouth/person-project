package com.example.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 考试结果
 * @author zb
 * @date 2019-04-01 19:01
 **/
@Document(value = "exam_result")
public class ExamResult extends DomainId {
    /**
     * 试卷
     */
    private String questionPaperId;
    /**
     * 开始考试时间
     */
    private LocalDateTime examStartTime;
    /**
     * 结束考试时间
     */
    private LocalDateTime examEndTime;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 考试分数
     */
    private Integer score;
    /**
     * 回答正确题
     */
    @DBRef(lazy = true)
    private List<Question> rightQuestions;
    /**
     * 回答错误题
     */
    @DBRef(lazy = true)
    private List<Question> wrongQuestions;
    /**
     * 是否通过考试
     */
    private boolean done = false;

    /**
     * 答题卡 {key: 试题ID, value: 提交的答案}
     */
    private Map<String, List<String>> answerSheet;

    @Override
    public String toString() {
        return "ExamResult{" +
                "questionPaperId='" + questionPaperId + '\'' +
                ", examStartTime=" + examStartTime +
                ", examEndTime=" + examEndTime +
                ", userId='" + userId + '\'' +
                ", score=" + score +
                ", rightQuestions=" + rightQuestions +
                ", wrongQuestions=" + wrongQuestions +
                ", done=" + done +
                ", answerSheet=" + answerSheet +
                '}';
    }

    public String getQuestionPaperId() {
        return questionPaperId;
    }

    public void setQuestionPaperId(String questionPaperId) {
        this.questionPaperId = questionPaperId;
    }

    public LocalDateTime getExamStartTime() {
        return examStartTime;
    }

    public void setExamStartTime(LocalDateTime examStartTime) {
        this.examStartTime = examStartTime;
    }

    public LocalDateTime getExamEndTime() {
        return examEndTime;
    }

    public void setExamEndTime(LocalDateTime examEndTime) {
        this.examEndTime = examEndTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<Question> getRightQuestions() {
        return rightQuestions;
    }

    public void setRightQuestions(List<Question> rightQuestions) {
        this.rightQuestions = rightQuestions;
    }

    public List<Question> getWrongQuestions() {
        return wrongQuestions;
    }

    public void setWrongQuestions(List<Question> wrongQuestions) {
        this.wrongQuestions = wrongQuestions;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Map<String, List<String>> getAnswerSheet() {
        return answerSheet;
    }

    public void setAnswerSheet(Map<String, List<String>> answerSheet) {
        this.answerSheet = answerSheet;
    }
}
