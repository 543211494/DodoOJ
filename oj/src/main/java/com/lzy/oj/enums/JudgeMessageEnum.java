package com.lzy.oj.enums;

public enum JudgeMessageEnum {

    ACCEPTED("Accepted", "通过"),
    WRONG_ANSWER("Wrong Answer", "答案错误"),
    COMPILE_ERROR("Compile Error", "编译错误"),
    MEMORY_LIMIT_EXCEEDED("", "内存溢出"),
    TIME_LIMIT_EXCEEDED("Time Limit Exceeded", "超时"),
    PRESENTATION_ERROR("Presentation Error", "展示错误"),
    WAITING("Waiting", "等待中"),
    OUTPUT_LIMIT_EXCEEDED("Output Limit Exceeded", "输出溢出"),
    DANGEROUS_OPERATION("Dangerous Operation", "危险操作"),
    SYSTEM_ERROR("System Error", "系统错误"),
    SUCCESS("Success", "成功运行"),
    RUNTIME_ERROR("Runtime Error", "运行时错误");

    private String text;

    private String value;

    JudgeMessageEnum(String text, String value) {
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
