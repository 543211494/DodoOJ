package com.lzy.oj.judge.sandbox.impl;

import com.lzy.oj.enums.JudgeMessageEnum;
import com.lzy.oj.enums.SubmitStatusEnum;
import com.lzy.oj.judge.sandbox.CodeSandBox;
import com.lzy.oj.judge.sandbox.dto.ExecuteCodeRequest;
import com.lzy.oj.judge.sandbox.dto.ExecuteCodeResponse;
import com.lzy.oj.judge.sandbox.dto.JudgeInfo;

import java.util.ArrayList;
import java.util.List;

public class JavaCodeSandbox implements CodeSandBox {

    /* 构造一个用于测试的response */
    private ExecuteCodeResponse test(){
        ExecuteCodeResponse response = new ExecuteCodeResponse();
        List<String> outputList = new ArrayList<>();
        outputList.add("2\n");
        outputList.add("3\n");
        response.setOutputList(outputList);
        response.setMessage(JudgeMessageEnum.SUCCESS.getText());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        judgeInfo.setMessage(JudgeMessageEnum.SUCCESS.getText());
        response.setJudgeInfo(judgeInfo);
        return response;
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("RemoteCodeSandbox");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return test();
    }
}
