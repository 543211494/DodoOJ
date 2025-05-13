package com.lzy.oj.bean.po;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.lzy.oj.bean.entity.QuestionSubmit;
import com.lzy.oj.judge.sandbox.dto.JudgeInfo;
import lombok.Data;

import java.util.Date;

@Data
@TableName("submit")
public class QuestionSubmitPO {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("language")
    private String language;

    @TableField("code")
    private String code;

    @TableField("judge_info")
    private String judgeInfo;

    @TableField("status")
    private Integer status;

    @TableField("question_id")
    private Long questionId;

    @TableField("question_title")
    private String questionTitle;

    @TableField("uid")
    private Long uid;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @TableField("is_delete")
    private Integer isDelete;

    public static QuestionSubmit po2Submit(QuestionSubmitPO questionSubmitPO){
        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setId(questionSubmitPO.getId());
        questionSubmit.setLanguage(questionSubmitPO.getLanguage());
        questionSubmit.setCode(questionSubmitPO.getCode());
        questionSubmit.setJudgeInfo(JSON.parseObject(questionSubmitPO.getJudgeInfo(), JudgeInfo.class));
        questionSubmit.setStatus(questionSubmitPO.getStatus());
        questionSubmit.setQuestionId(questionSubmitPO.getQuestionId());
        questionSubmit.setQuestionTitle(questionSubmitPO.getQuestionTitle());
        questionSubmit.setUid(questionSubmitPO.getUid());
        questionSubmit.setCreateTime(questionSubmitPO.getCreateTime());
        questionSubmit.setUpdateTime(questionSubmitPO.getUpdateTime());
        questionSubmit.setIsDelete(questionSubmitPO.getIsDelete());
        return questionSubmit;
    }

    public static QuestionSubmitPO submit2PO(QuestionSubmit questionSubmit){
        QuestionSubmitPO questionSubmitPO = new QuestionSubmitPO();
        questionSubmitPO.setId(questionSubmit.getId());
        questionSubmitPO.setLanguage(questionSubmit.getLanguage());
        questionSubmitPO.setCode(questionSubmit.getCode());
        questionSubmitPO.setJudgeInfo(JSON.toJSONString(questionSubmit.getJudgeInfo()));
        questionSubmitPO.setStatus(questionSubmit.getStatus());
        questionSubmitPO.setQuestionId(questionSubmit.getQuestionId());
        questionSubmitPO.setQuestionTitle(questionSubmit.getQuestionTitle());
        questionSubmitPO.setUid(questionSubmit.getUid());
        questionSubmitPO.setCreateTime(questionSubmit.getCreateTime());
        questionSubmitPO.setUpdateTime(questionSubmit.getUpdateTime());
        questionSubmitPO.setIsDelete(questionSubmit.getIsDelete());
        return questionSubmitPO;
    }
}
