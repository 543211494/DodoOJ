package com.lzy.sandbox4j.sandbox;

import com.lzy.sandbox4j.sandbox.dto.ExecuteCodeRequest;
import com.lzy.sandbox4j.sandbox.dto.ExecuteCodeResponse;

public interface CodeSandBox {

    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}
