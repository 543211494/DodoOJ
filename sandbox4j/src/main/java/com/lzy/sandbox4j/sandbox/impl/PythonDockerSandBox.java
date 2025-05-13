package com.lzy.sandbox4j.sandbox.impl;

import com.lzy.sandbox4j.sandbox.dto.ExecuteMessage;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class PythonDockerSandBox extends DockerSandBoxTemplate{

    @Override
    public ExecuteMessage compile(File codeFile){
        ExecuteMessage compileMessage = new ExecuteMessage();
        compileMessage.setExitValue(0);
        return compileMessage;
    }

    @Override
    public String codeFileName(){
        return "main.py";
    }

    @Override
    public String imageName(){
        return "python:3.9-alpine";
    }

    @Override
    public String createCompileCmd(String filePath){
        return null;
    }

    @Override
    public String createRunCmd(String path){
        return String.format("python %s/main.py", path);
    }

    public static void main(String[] args) {
        /**
         * 使用docker-java库下载镜像有时候会很慢甚至卡住报错
         * 尽量自己手动准备好镜像
         */
        PythonDockerSandBox sandBox = new PythonDockerSandBox();
        sandBox.downloadImage("python:3.9-alpine");
    }
}
