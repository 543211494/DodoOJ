package com.lzy.oj.controller;

import com.lzy.oj.annotation.AuthCheck;
import com.lzy.oj.bean.dto.ResponseDTO;
import com.lzy.oj.bean.dto.submit.SubmitDTO;
import com.lzy.oj.constant.UserRoleConstant;
import com.lzy.oj.enums.LanguageEnum;
import com.lzy.oj.service.QuestionSubmitService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/submit")
public class QuestionSubmitController {

    @Resource
    private QuestionSubmitService questionSubmitService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @AuthCheck(role = UserRoleConstant.USER)
    public ResponseDTO<Long> submit(@RequestBody SubmitDTO submitDTO){
        return ResponseDTO.success(questionSubmitService.judgeQuestion(submitDTO));
    }
}
