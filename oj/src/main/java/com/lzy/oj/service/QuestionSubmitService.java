package com.lzy.oj.service;

import com.lzy.oj.bean.dto.submit.SubmitDTO;
import com.lzy.oj.bean.entity.QuestionSubmit;

public interface QuestionSubmitService {

    public Long judgeQuestion(SubmitDTO submitDTO);

    public Long addQuestionSubmit(QuestionSubmit questionSubmit);

    public QuestionSubmit searchQuestionSubmitById(Long id);

    public Boolean updateSubmitById(QuestionSubmit questionSubmit);
}
