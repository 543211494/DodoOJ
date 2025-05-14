package com.lzy.oj.judge.sandbox;

import com.lzy.oj.judge.sandbox.impl.JavaCodeSandBox;
import com.lzy.oj.judge.sandbox.impl.PythonCodeSandBox;

public class CodeSandboxFactory {

    public static CodeSandBox newInstance(String type) {
        System.out.println(type);
        switch (type) {
            case "java":
                return new JavaCodeSandBox();
            case "python":
                return new PythonCodeSandBox();
            default:
                return new JavaCodeSandBox();
        }
    }
}
