package com.lzy.oj.enums;

public enum ErrorEnum {
    REGISTER_ERROR(100,"帐号已存在"),
    PASSWORD_NOT_EQUAL_ERROR(101,"密码不一致");

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
