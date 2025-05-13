package com.lzy.oj.bean.dto.submit;

import lombok.Data;

@Data
public class SubmitListDTO {

    /**
     * 页码，从1开始
     */
    private Integer currentPage;

    private Integer pageSize;

    private Long uid;

    private Long questionId;
}
