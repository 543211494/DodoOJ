package com.lzy.oj.bean.dto.submit;

import com.lzy.oj.bean.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

@Data
public class SubmitListResponseDTO {

    private Long pageNum;

    private List<QuestionSubmit> submitList;
}
