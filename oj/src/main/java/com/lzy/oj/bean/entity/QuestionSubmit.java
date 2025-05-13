package com.lzy.oj.bean.entity;

import com.lzy.oj.judge.sandbox.dto.JudgeInfo;
import lombok.Data;

import java.util.Date;

@Data
public class QuestionSubmit {

    private Long id;

    private String language;

    private String code;

    /* 判题结果 */
    private JudgeInfo judgeInfo;

    private Integer status;

    private Long questionId;

    private String questionTitle;

    private Long uid;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;
}
