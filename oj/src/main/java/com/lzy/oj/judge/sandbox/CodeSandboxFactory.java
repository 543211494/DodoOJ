package com.lzy.oj.judge.sandbox;

import com.lzy.oj.enums.LanguageEnum;
import com.lzy.oj.judge.sandbox.impl.JavaCodeSandbox;

public class CodeSandboxFactory {

    public static CodeSandBox newInstance(String type) {
        switch (type) {
            case "JAVA":
                return new JavaCodeSandbox();
            default:
                return new JavaCodeSandbox();
        }
    }
}
