package com.lzy.oj.exception;


import com.lzy.oj.bean.dto.ResponseDTO;
import com.lzy.oj.enums.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionResolver {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    private ResponseDTO processError(Exception e){
//        System.out.println(e.getMessage());
        return ResponseDTO.fail(e.getMessage());
    }
}
