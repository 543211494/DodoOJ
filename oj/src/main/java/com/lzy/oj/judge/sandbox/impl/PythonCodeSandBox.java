package com.lzy.oj.judge.sandbox.impl;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.lzy.oj.judge.sandbox.CodeSandBox;
import com.lzy.oj.judge.sandbox.CodeSandBoxConfig;
import com.lzy.oj.judge.sandbox.dto.ExecuteCodeRequest;
import com.lzy.oj.judge.sandbox.dto.ExecuteCodeResponse;

public class PythonCodeSandBox implements CodeSandBox {

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        String url = CodeSandBoxConfig.getPythonUrl();
        String json = JSON.toJSONString(executeCodeRequest);
        String response = HttpUtil.createPost(url)
                .body(json)
                .execute()
                .body();
        return JSON.parseObject(response,ExecuteCodeResponse.class);
    }
}
