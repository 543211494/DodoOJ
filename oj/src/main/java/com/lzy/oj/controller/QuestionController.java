package com.lzy.oj.controller;

import com.alibaba.fastjson.JSON;
import com.lzy.oj.annotation.AuthCheck;
import com.lzy.oj.bean.dto.ResponseDTO;
import com.lzy.oj.bean.dto.question.QuestionAddDTO;
import com.lzy.oj.bean.entity.Question;
import com.lzy.oj.bean.po.QuestionPO;
import com.lzy.oj.constant.UserRoleConstant;
import com.lzy.oj.service.QuestionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @AuthCheck(role = UserRoleConstant.ADMIN)
    public ResponseDTO<Long> addQuestion(@RequestBody QuestionAddDTO questionAddDTO){
        Question question = new Question();
        question.setTitle(questionAddDTO.getTitle());
        question.setContent(questionAddDTO.getContent());
        question.setTags(JSON.toJSONString(questionAddDTO.getTags()));
        question.setAnswer(questionAddDTO.getAnswer());
        question.setJudgeCase(JSON.toJSONString(questionAddDTO.getJudgeCase()));
        question.setJudgeConfig(JSON.toJSONString(questionAddDTO.getJudgeConfig()));
        question.setUid(questionAddDTO.getUser().getId());
        return ResponseDTO.success(questionService.addQuestion(question));
    }
}
