package com.lzy.oj.controller;

import com.alibaba.fastjson.JSON;
import com.lzy.oj.annotation.AuthCheck;
import com.lzy.oj.bean.dto.ResponseDTO;
import com.lzy.oj.bean.dto.question.*;
import com.lzy.oj.bean.entity.Question;
import com.lzy.oj.constant.UserRoleConstant;
import com.lzy.oj.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @AuthCheck(role = UserRoleConstant.ADMIN)
    public ResponseDTO<Long> addQuestion(@RequestBody QuestionAddDTO questionAddDTO){
        Question question = new Question();
        /* 在末尾添加换行符 */
        List<JudgeCase> judgeCases = questionAddDTO.getJudgeCase();
        if(judgeCases!=null){
            for(int i = 0;i<judgeCases.size();i++){
                JudgeCase judgeCase = judgeCases.get(i);
                if(!judgeCase.getInput().endsWith("\n")){
                    judgeCase.setInput(judgeCase.getInput()+"\n");
                }
                if(!judgeCase.getOutput().endsWith("\n")){
                    judgeCase.setOutput(judgeCase.getOutput()+"\n");
                }
            }
        }
        question.setTitle(questionAddDTO.getTitle());
        question.setContent(questionAddDTO.getContent());
        question.setTags(questionAddDTO.getTags());
        question.setAnswer(questionAddDTO.getAnswer());
        question.setJudgeCase(questionAddDTO.getJudgeCase());
        question.setJudgeConfig(questionAddDTO.getJudgeConfig());
        question.setUid(questionAddDTO.getUser().getId());
        return ResponseDTO.success(questionService.addQuestion(question));
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public ResponseDTO<QuestionListResponseDTO> listQuestion(@RequestBody QuestionListDTO questionListDTO){
        QuestionListResponseDTO responseDTO = new QuestionListResponseDTO();
        Integer currentPage = questionListDTO.getCurrentPage();
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
