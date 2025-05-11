package com.lzy.sandbox4j.controller;

import com.lzy.sandbox4j.sandbox.dto.ExecuteCodeRequest;
import com.lzy.sandbox4j.sandbox.dto.ExecuteCodeResponse;
import com.lzy.sandbox4j.sandbox.impl.JavaDockerSandBox;
import com.lzy.sandbox4j.sandbox.impl.JavaNativeSandBox;
import com.lzy.sandbox4j.sandbox.impl.SandBoxTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("/")
public class SandBoxController {

    @Resource
    private JavaNativeSandBox javaNativeSandBox;

    @Resource
    private JavaDockerSandBox javaDockerSandBox;

    @PostMapping("/java/executeCode")
    ExecuteCodeResponse executeCode(@RequestBody ExecuteCodeRequest executeCodeRequest) {
        //return javaNativeSandBox.executeCode(executeCodeRequest);
        return javaDockerSandBox.executeCode(executeCodeRequest);
    }
}
