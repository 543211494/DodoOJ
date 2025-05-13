package com.lzy.oj.judge.sandbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.lzy.oj.enums.JudgeMessageEnum;
import com.lzy.oj.enums.SubmitStatusEnum;
import com.lzy.oj.judge.sandbox.CodeSandBox;
import com.lzy.oj.judge.sandbox.dto.ExecuteCodeRequest;
import com.lzy.oj.judge.sandbox.dto.ExecuteCodeResponse;
import com.lzy.oj.judge.sandbox.dto.JudgeInfo;

import java.util.ArrayList;
import java.util.List;

public class JavaCodeSandbox implements CodeSandBox {

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        String url = "http://localhost:8081/api/java/executeCode";
        String json = JSON.toJSONString(executeCodeRequest);
        System.out.println(json);
        String response = HttpUtil.createPost(url)
                .body(json)
                .execute()
                .body();
        System.out.println(response);
        return JSON.parseObject(response,ExecuteCodeResponse.class);
    }
}
