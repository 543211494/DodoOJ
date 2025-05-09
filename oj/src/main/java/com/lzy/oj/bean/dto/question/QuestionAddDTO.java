package com.lzy.oj.bean.dto.question;

import com.lzy.oj.bean.dto.RequestDTO;
import lombok.Data;

import java.util.List;

@Data
public class QuestionAddDTO extends RequestDTO {

    private String title;

    private String content;

    private List<String> tags;

    private String answer;

    private List<JudgeCase> judgeCase;

    private JudgeConfig judgeConfig;
}
