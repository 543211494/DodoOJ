package com.lzy.oj.bean.po;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lzy.oj.bean.dto.question.JudgeCase;
import com.lzy.oj.bean.dto.question.JudgeConfig;
import com.lzy.oj.bean.entity.Question;
import lombok.Data;

import java.util.Date;

@Data
@TableName("question")
public class QuestionPO {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("title")
    private String title;

    @TableField("content")
    private String content;

    @TableField("tags")
    private String tags;

    @TableField("answer")
    private String answer;

    @TableField("submit_count")
    private Integer submitCount;

    @TableField("accepted_count")
    private Integer acceptedCount;

    @TableField("judge_case")
    private String judgeCase;

    @TableField("judge_config")
    private String judgeConfig;

    @TableField("thumb_num")
    private Integer thumbNum;

    @TableField("favour_num")
    private Integer favourNum;

    @TableField("uid")
    private Long uid;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @TableField("is_delete")
    private Integer isDelete;

    public static Question po2Question(QuestionPO questionPO){
        Question question = new Question();
        question.setId(questionPO.getId());
        question.setTitle(questionPO.getTitle());
        question.setContent(questionPO.getContent());
        question.setTags(questionPO.getTags());
        question.setAnswer(questionPO.getAnswer());
        question.setSubmitCount(questionPO.getSubmitCount());
        question.setAcceptedCount(questionPO.getAcceptedCount());
        question.setJudgeCase(JSON.parseArray(questionPO.getJudgeCase(), JudgeCase.class));
        question.setJudgeConfig(JSON.parseObject(questionPO.getJudgeConfig(), JudgeConfig.class));
        question.setThumbNum(questionPO.getThumbNum());
        question.setFavourNum(questionPO.getFavourNum());
        question.setUid(questionPO.getUid());
        question.setCreateTime(questionPO.getCreateTime());
        question.setUpdateTime(questionPO.getUpdateTime());
        question.setIsDelete(questionPO.getIsDelete());
        return question;
    }

    public static QuestionPO question2PO(Question question){
        QuestionPO questionPO = new QuestionPO();
        questionPO.setId(question.getId());
        questionPO.setTitle(question.getTitle());
        questionPO.setContent(question.getContent());
        questionPO.setTags(question.getTags());
        questionPO.setAnswer(question.getAnswer());
        questionPO.setSubmitCount(question.getSubmitCount());
        questionPO.setAcceptedCount(question.getAcceptedCount());
        questionPO.setJudgeCase(JSON.toJSONString(question.getJudgeCase()));
        questionPO.setJudgeConfig(JSON.toJSONString(question.getJudgeConfig()));
        questionPO.setThumbNum(question.getThumbNum());
        questionPO.setFavourNum(question.getFavourNum());
        questionPO.setUid(question.getUid());
        questionPO.setCreateTime(question.getCreateTime());
        questionPO.setUpdateTime(question.getUpdateTime());
        questionPO.setIsDelete(question.getIsDelete());
        return questionPO;
    }
}
