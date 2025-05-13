package com.lzy.oj.judge.sandbox;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CodeSandBoxConfig {

    @Value("${codesandbox.java.host}")
    private String javaHost;

    @Value("${codesandbox.java.port}")
    private Integer javaPort;

    private static String javaURL;

    @PostConstruct
    public void init() {
        javaURL = "http://"+this.javaHost+":"+javaPort+"/api/java/executeCode";
    }

    public static String getJavaUrl() {
        return javaURL;
    }
}
