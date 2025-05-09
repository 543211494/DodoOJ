package com.lzy.oj.bean.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Question {

    private Long id;

    private String title;

    private String content;

    private String tags;

    private String answer;

    private Integer submitCount;

    private Integer acceptedCount;

    private String judgeCase;

    private String judgeConfig;

    private Integer thumbNum;

    private Integer favourNum;

    private Long uid;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;
}
