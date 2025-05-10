package com.lzy.sandbox4j.enums;

public enum ExecuteEnum {
    COMPILE_ERROR("Compile Error", "编译错误"),
    MEMORY_LIMIT_EXCEEDED("", "内存溢出"),
    TIME_LIMIT_EXCEEDED("Time Limit Exceeded", "超时"),
    SUCCESS("Success", "成功运行");

    private String text;

    private String value;

    ExecuteEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }
}
