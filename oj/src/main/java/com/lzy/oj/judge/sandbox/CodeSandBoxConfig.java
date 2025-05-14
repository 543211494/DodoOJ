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

    @Value("${codesandbox.python.host}")
    private String pythonHost;

    @Value("${codesandbox.python.port}")
    private Integer pythonPort;

    private static String javaURL;

    private static String pythonURL;

    @PostConstruct
    public void init() {
        javaURL = "http://"+this.javaHost+":"+javaPort+"/api/java/executeCode";
        pythonURL = "http://"+this.pythonHost+":"+pythonPort+"/api/python/executeCode";
    }

    public static String getJavaUrl() {
        return javaURL;
    }

    public static String getPythonUrl(){
        return pythonURL;
    }
}
