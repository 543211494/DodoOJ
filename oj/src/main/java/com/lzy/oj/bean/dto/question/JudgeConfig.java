package com.lzy.oj.bean.dto.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JudgeConfig {

    private Long timeLimit;

    private Long memoryLimit;

    private Long stackLimit;
}
