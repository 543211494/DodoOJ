package com.lzy.oj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzy.oj.bean.dto.submit.SubmitDTO;
import com.lzy.oj.bean.entity.Question;
import com.lzy.oj.bean.entity.QuestionSubmit;
import com.lzy.oj.bean.po.QuestionSubmitPO;
import com.lzy.oj.enums.ErrorEnum;
import com.lzy.oj.enums.JudgeMessageEnum;
import com.lzy.oj.enums.LanguageEnum;
import com.lzy.oj.enums.SubmitStatusEnum;
import com.lzy.oj.exception.BusinessException;
import com.lzy.oj.judge.JudgeService;
import com.lzy.oj.judge.sandbox.dto.JudgeInfo;
import com.lzy.oj.mapper.QuestionSubmitMapper;
import com.lzy.oj.service.QuestionService;
import com.lzy.oj.service.QuestionSubmitService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Repository
public class QuestionSubmitServiceImpl implements QuestionSubmitService {

    @Resource
    private QuestionSubmitMapper questionSubmitMapper;

    @Resource
    private QuestionService questionService;

    @Resource
    private JudgeService judgeService;

    @Override
    public Long judgeQuestion(SubmitDTO submitDTO) {
        if(LanguageEnum.getEnumByRole(submitDTO.getLanguage())==null){
            throw new BusinessException(ErrorEnum.LANGUAGE_ERROR);
        }
        Question question = questionService.searchQuestionById(submitDTO.getQuestionId());
        if(question==null){
            throw new BusinessException(ErrorEnum.QUESTION_NOT_EXIST_ERROR);
        }

        QuestionSubmit questionSubmit = new QuestionSubmit();
        questionSubmit.setUid(submitDTO.getUser().getId());
        questionSubmit.setQuestionId(submitDTO.getQuestionId());
        questionSubmit.setQuestionTitle(submitDTO.getQuestionTitle());
        questionSubmit.setCode(submitDTO.getCode());
        questionSubmit.setLanguage(submitDTO.getLanguage());
        questionSubmit.setStatus(SubmitStatusEnum.RUNNING.getValue());
        questionSubmit.setJudgeInfo(null);
        questionSubmit.setId(this.addQuestionSubmit(questionSubmit));

        /* 异步判题 */
        CompletableFuture.runAsync(() -> {
            JudgeInfo judgeInfo = judgeService.judge(questionSubmit, question);
            questionSubmit.setStatus(SubmitStatusEnum.SUCCEED.getValue());
            questionSubmit.setJudgeInfo(judgeInfo);
            this.updateSubmitById(questionSubmit);
            /* 更新提交信息 */
            int submit = 1;
            int ac = 0;
            if (judgeInfo.getMessage().equals(JudgeMessageEnum.ACCEPTED.getText())){
                ac++;
            }
            questionService.updateCount(submit, ac, submitDTO.getQuestionId());
        });

        return questionSubmit.getId();
    }

    @Override
    public Long addQuestionSubmit(QuestionSubmit questionSubmit) {
        QuestionSubmitPO questionSubmitPO = QuestionSubmitPO.submit2PO(questionSubmit);
        questionSubmitMapper.insert(questionSubmitPO);
        return questionSubmitPO.getId();
    }

    @Override
    public QuestionSubmit searchQuestionSubmitById(Long id) {
        QueryWrapper<QuestionSubmitPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        queryWrapper.eq("is_delete",0);
        return QuestionSubmitPO.po2Submit(questionSubmitMapper.selectList(queryWrapper).get(0));
    }

    @Override
    public Boolean updateSubmitById(QuestionSubmit questionSubmit) {
        return questionSubmitMapper.updateById(QuestionSubmitPO.submit2PO(questionSubmit)) > 0;
    }

    @Override
    public List<QuestionSubmit> listQuestionSubmit(Integer currentPage, Integer pageSize, Long uid, Long questionId) {
        Page<QuestionSubmitPO> page = new Page<>(currentPage,pageSize);
        QueryWrapper<QuestionSubmitPO> queryWrapper = new QueryWrapper<>();
        if(uid!=null){
            queryWrapper.eq("uid",uid);
        }
        if(questionId!=null){
            queryWrapper.eq("question_id",questionId);
        }
        queryWrapper.eq("is_delete",0);
        queryWrapper.orderByAsc("id");
        questionSubmitMapper.selectPage(page,queryWrapper);
        return page.getRecords().stream()
                .map(questionSubmitPO -> {
                    return QuestionSubmitPO.po2Submit(questionSubmitPO);
                }).collect(Collectors.toList());
    }

    @Override
    public Long countQuestionSubmit(Long uid, Long questionId) {
        QueryWrapper<QuestionSubmitPO> queryWrapper = new QueryWrapper<>();
        if(uid!=null){
            queryWrapper.eq("uid",uid);
        }
        if(questionId!=null){
            queryWrapper.eq("question_id",questionId);
        }
        queryWrapper.eq("is_delete",0);
        return questionSubmitMapper.selectCount(queryWrapper);
    }

}
