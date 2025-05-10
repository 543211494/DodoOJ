package com.lzy.oj.judge.strategy;

import com.lzy.oj.judge.sandbox.dto.JudgeInfo;

public interface JudgeStrategy {

    JudgeInfo doJudge(JudgeContext judgeContext);
}
