package com.lzy.oj.controller;

import com.alibaba.fastjson.JSON;
import com.lzy.oj.annotation.AuthCheck;
import com.lzy.oj.bean.dto.ResponseDTO;
import com.lzy.oj.bean.dto.question.QuestionAddDTO;
import com.lzy.oj.bean.dto.question.QuestionDeleteDTO;
import com.lzy.oj.bean.dto.question.QuestionListDTO;
import com.lzy.oj.bean.dto.question.QuestionListResponseDTO;
import com.lzy.oj.bean.entity.Question;
import com.lzy.oj.constant.UserRoleConstant;
import com.lzy.oj.service.QuestionService;
import org.springframework.web.bind.annotation.*;

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
        question.setJudgeCase(questionAddDTO.getJudgeCase());
        question.setJudgeConfig(questionAddDTO.getJudgeConfig());
        question.setUid(questionAddDTO.getUser().getId());
        return ResponseDTO.success(questionService.addQuestion(question));
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public ResponseDTO<QuestionListResponseDTO> listQuestion(@RequestBody QuestionListDTO questionListDTO){
        QuestionListResponseDTO responseDTO = new QuestionListResponseDTO();
        Integer currentPage = questionListDTO.getCurrentpage();
        Integer pageSize = questionListDTO.getPageSize();
        Integer sum = questionService.countQuestions().intValue();
        responseDTO.setPageNum((long) (sum/pageSize + (sum%pageSize==0?0:1)));
        responseDTO.setQuestions(questionService.listQuestion(currentPage,pageSize));
        return ResponseDTO.success(responseDTO);
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @AuthCheck(role = UserRoleConstant.ADMIN)
    public ResponseDTO<Boolean> deleteQuestion(@RequestBody QuestionDeleteDTO questionDeleteDTO){
        return ResponseDTO.success(questionService.deleteQuestion(questionDeleteDTO.getId()));
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public ResponseDTO<Question> searchQuestion(@RequestParam("id") Long id){
        Question question = questionService.searchQuestionById(id);
        question.setAnswer(null);
        question.setJudgeCase(null);
        return ResponseDTO.success(question);
    }
}
