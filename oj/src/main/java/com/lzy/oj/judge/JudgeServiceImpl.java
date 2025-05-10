package com.lzy.oj.judge;

import com.alibaba.fastjson.JSON;
import com.lzy.oj.bean.dto.question.JudgeCase;
import com.lzy.oj.bean.entity.Question;
import com.lzy.oj.bean.entity.QuestionSubmit;
import com.lzy.oj.judge.sandbox.CodeSandBox;
import com.lzy.oj.judge.sandbox.CodeSandboxFactory;
import com.lzy.oj.judge.sandbox.dto.ExecuteCodeRequest;
import com.lzy.oj.judge.sandbox.dto.ExecuteCodeResponse;
import com.lzy.oj.judge.sandbox.dto.JudgeInfo;
import com.lzy.oj.judge.strategy.JudgeContext;
import com.lzy.oj.judge.strategy.JudgeStrategy;
import com.lzy.oj.judge.strategy.JudgeStrategyFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImpl implements JudgeService{

    @Override
    public JudgeInfo judge(QuestionSubmit questionSubmit, Question question) {
        CodeSandBox codeSandBox = CodeSandboxFactory.newInstance(questionSubmit.getLanguage());
        /* 构造请求,执行代码 */
        List<JudgeCase> cases = question.getJudgeCase();
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(questionSubmit.getCode())
                .language(questionSubmit.getLanguage())
                .inputList(cases.stream().map(JudgeCase::getInput).collect(Collectors.toList()))
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
        /* 判题 */
        JudgeStrategy judgeStrategy = JudgeStrategyFactory.newInstance(questionSubmit.getLanguage());
        return judgeStrategy.doJudge(new JudgeContext(executeCodeResponse, question, questionSubmit));
    }
}
