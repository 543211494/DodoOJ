package com.lzy.oj.bean.dto.question;

import com.lzy.oj.bean.entity.Question;
import lombok.Data;

import java.util.List;

@Data
public class QuestionListResponseDTO {

    private Long pageNum;

    private List<Question> questions;
}
