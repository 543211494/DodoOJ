package com.lzy.oj.judge.strategy;

import com.lzy.oj.bean.entity.Question;
import com.lzy.oj.bean.entity.QuestionSubmit;
import com.lzy.oj.judge.sandbox.dto.ExecuteCodeResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JudgeContext {

    private ExecuteCodeResponse response;

    private Question question;

    private QuestionSubmit questionSubmit;
}
