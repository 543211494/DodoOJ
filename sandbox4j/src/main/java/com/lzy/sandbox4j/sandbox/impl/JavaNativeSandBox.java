package com.lzy.sandbox4j.sandbox.impl;

import org.springframework.stereotype.Component;

@Component
public class JavaNativeSandBox extends SandBoxTemplate{

    @Override
    public String createCompileCmd(String filePath){
        return String.format("javac -encoding utf-8 %s", filePath);
    }

    @Override
    public String createRunCmd(String path){
        return String.format("java -Xmx256m -Dfile.encoding=UTF-8 -cp %s Main", path);
    }

    @Override
    public String codeFileName(){
        return "Main.java";
    }
}
