package com.lzy.oj.bean.dto.question;

import lombok.Data;

@Data
public class QuestionListDTO{

    /**
     * 页码，从1开始
     */
    private Integer currentpage;

    private Integer pageSize;
}
