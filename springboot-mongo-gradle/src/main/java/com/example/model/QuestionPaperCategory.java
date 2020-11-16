package com.example.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 试卷分类
 * @author zb
 * @date 2019-03-31 14:30
 **/
@Document("question_paper_category")
public class QuestionPaperCategory extends DomainId {

    /**
     * 课程编码
     */
    private String code;
    /**
     * 课程分类名称
     */
    private String name;
    /**
     * 父级分类
     */
    @DBRef(lazy = true)
    private QuestionPaperCategory parent;

    @Override
    public String toString() {
        return "QuestionPaperCategory{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
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

    public QuestionPaperCategory getParent() {
        return parent;
    }

    public void setParent(QuestionPaperCategory parent) {
        this.parent = parent;
    }
}
