package com.lzy.oj.enums;

public enum ErrorEnum {
    REGISTER_ERROR(100,"帐号已存在"),
    PASSWORD_NOT_EQUAL_ERROR(101,"密码不一致"),
    LOGIN_ERROR(102,"帐号密码错误"),
    NO_LOGIN_ERROR(103,"未登录"),
    ROLE_ERROR(104,"没有操作权限"),
    LANGUAGE_ERROR(105,"暂时不支持该语言"),
    QUESTION_NOT_EXIST_ERROR(106,"问题不存在"),
    PARAM_ERROR(107,"参数为空或格式错误");

    private Integer code;
    private String message;

    ErrorEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ErrorEnum getEnumByCode(Integer code){
        if(code==null){
            return null;
        }
        for(ErrorEnum errorEnum : ErrorEnum.values()){
            if(errorEnum.code.intValue()==code.intValue()){
                return errorEnum;
            }
        }
        return null;
    }

    public static ErrorEnum getEnumByMessage(String message){
        if(message==null){
            return null;
        }
        for(ErrorEnum errorEnum : ErrorEnum.values()){
            if(errorEnum.message.equals(message)){
                return errorEnum;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
