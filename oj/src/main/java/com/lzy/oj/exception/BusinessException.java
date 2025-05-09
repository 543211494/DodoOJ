package com.lzy.oj.exception;

import com.lzy.oj.enums.ErrorEnum;
import lombok.Data;

public class BusinessException extends RuntimeException{

    private final Integer code;

    public BusinessException(ErrorEnum errorEnum) {
        super(errorEnum.getMessage());
        this.code = errorEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
