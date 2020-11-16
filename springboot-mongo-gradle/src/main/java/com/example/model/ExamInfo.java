package com.example.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author hqq
 * @date 2019-04-06 10:23
 **/
@Getter
@Setter
public class ExamInfo {
    /**
     * 试卷
     */
    private String id;
    /**
     * 试卷名称
     */
    private String name;
    /**
     * 试卷级别。 类型：SINGLE_CHOICE(单选题)、MULTIPLE_CHOICE(多选题)、JUDGE_CHOICE(判断题)
     */
    private String difficultyLevel;
    /**
     * 考试次数
     */
    private Integer count;
    /**
     * 序号
     */
    private Integer sortNo;
    /**
     * 是否完成
     */
    private Boolean done;


}
