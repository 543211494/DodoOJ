package com.lzy.oj.bean.entity;

import com.lzy.oj.bean.dto.question.JudgeCase;
import com.lzy.oj.bean.dto.question.JudgeConfig;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Question {

    private Long id;

    private String title;

    private String content;

    private String tags;

    private String answer;

    private Integer submitCount;

    private Integer acceptedCount;

    private List<JudgeCase> judgeCase;

    private JudgeConfig judgeConfig;

    private Integer thumbNum;

    private Integer favourNum;

    private Long uid;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;
}
