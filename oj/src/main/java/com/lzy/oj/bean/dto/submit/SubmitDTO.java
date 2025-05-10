package com.lzy.oj.bean.dto.submit;

import com.lzy.oj.bean.dto.RequestDTO;
import lombok.Data;

@Data
public class SubmitDTO extends RequestDTO {

    private String language;

    private String code;

    private Long questionId;
}
