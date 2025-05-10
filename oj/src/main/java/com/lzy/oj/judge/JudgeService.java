package com.lzy.oj.judge;

import com.lzy.oj.bean.entity.Question;
import com.lzy.oj.bean.entity.QuestionSubmit;
import com.lzy.oj.judge.sandbox.dto.JudgeInfo;

public interface JudgeService {

    JudgeInfo judge(QuestionSubmit questionSubmit, Question question);
}
