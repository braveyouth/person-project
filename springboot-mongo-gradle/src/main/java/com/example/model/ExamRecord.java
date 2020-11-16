package com.example.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author zb
 * @date 2019-04-01 18:58
 **/
@Document(value = "exam_record")
public class ExamRecord extends DomainId {

    /**
     * 用户信息
     */
    private String userId;
    /**
     * 试卷ID
     */
    private String questionPaperId;
    /**
     * 考试结果ID
     */
    @DBRef(lazy = true)
    private List<ExamResult> examResults;
    /**
     * 是否完成考试
     */
    private boolean done = false;

    @Override
    public String toString() {
        return "ExamRecord{" +
                "userId='" + userId + '\'' +
                ", questionPaperId='" + questionPaperId + '\'' +
                ", examResults=" + examResults +
                ", done=" + done +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuestionPaperId() {
        return questionPaperId;
    }

    public void setQuestionPaperId(String questionPaperId) {
        this.questionPaperId = questionPaperId;
    }

    public List<ExamResult> getExamResults() {
        return examResults;
    }

    public void setExamResults(List<ExamResult> examResults) {
        this.examResults = examResults;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
