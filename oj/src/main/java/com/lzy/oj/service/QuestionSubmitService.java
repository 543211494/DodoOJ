package com.lzy.oj.service;

import com.lzy.oj.bean.dto.submit.SubmitDTO;
import com.lzy.oj.bean.entity.QuestionSubmit;

import java.util.List;

public interface QuestionSubmitService {

    public Long judgeQuestion(SubmitDTO submitDTO);

    public Long addQuestionSubmit(QuestionSubmit questionSubmit);

    public QuestionSubmit searchQuestionSubmitById(Long id);

    public Boolean updateSubmitById(QuestionSubmit questionSubmit);

    public List<QuestionSubmit> listQuestionSubmit(Integer currentPage, Integer pageSize,Long uid,Long questionId);

    public Long countQuestionSubmit(Long uid, Long questionId);
}
