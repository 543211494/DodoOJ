package com.lzy.oj.judge.strategy.impl;

import com.lzy.oj.bean.dto.question.JudgeCase;
import com.lzy.oj.bean.dto.question.JudgeConfig;
import com.lzy.oj.enums.JudgeMessageEnum;
import com.lzy.oj.judge.sandbox.dto.JudgeInfo;
import com.lzy.oj.judge.strategy.JudgeContext;
import com.lzy.oj.judge.strategy.JudgeStrategy;

import java.util.List;
import java.util.stream.Collectors;

public class JavaJudgeStrategy implements JudgeStrategy {
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        /* 程序执行信息 */
        JudgeInfo executeInfo = judgeContext.getResponse().getJudgeInfo();
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMemory(executeInfo.getMemory());
        judgeInfo.setTime(executeInfo.getTime());
        if(!judgeContext.getResponse().getMessage().equals(JudgeMessageEnum.SUCCESS.getText())){
            judgeInfo.setMessage(executeInfo.getMessage());
            return judgeInfo;
        }

        List<String> output = judgeContext.getResponse().getOutputList();
        List<String> answer = judgeContext.getQuestion().getJudgeCase()
                .stream()
                .map(JudgeCase::getOutput)
                .collect(Collectors.toList());
        JudgeConfig limitConfig = judgeContext.getQuestion().getJudgeConfig();

        /* 检查输出 */
        if(output.size()!=answer.size()){
            judgeInfo.setMessage(JudgeMessageEnum.WRONG_ANSWER.getText());
            return judgeInfo;
        }
        for(int i = 0;i<answer.size();i++){
            if(!output.get(i).equals(answer.get(i))){
                judgeInfo.setMessage(JudgeMessageEnum.WRONG_ANSWER.getText());
                return judgeInfo;
            }
        }

        /*  检查限制条件 */
        if(executeInfo.getMemory().longValue() > limitConfig.getMemoryLimit().longValue()){
            judgeInfo.setMessage(JudgeMessageEnum.MEMORY_LIMIT_EXCEEDED.getText());
            return judgeInfo;
        }

        if(executeInfo.getTime().longValue() > limitConfig.getTimeLimit().longValue()){
            judgeInfo.setMessage(JudgeMessageEnum.TIME_LIMIT_EXCEEDED.getText());
            return judgeInfo;
        }

        judgeInfo.setMessage(JudgeMessageEnum.ACCEPTED.getText());
        return judgeInfo;
    }
}
