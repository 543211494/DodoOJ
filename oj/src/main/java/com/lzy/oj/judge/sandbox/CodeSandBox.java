package com.lzy.oj.judge.sandbox;

import com.lzy.oj.judge.sandbox.dto.ExecuteCodeRequest;
import com.lzy.oj.judge.sandbox.dto.ExecuteCodeResponse;

public interface CodeSandBox {

    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
