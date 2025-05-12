package com.lzy.oj.judge.strategy;

import com.lzy.oj.judge.strategy.impl.JavaJudgeStrategy;

public class JudgeStrategyFactory {

    public static JudgeStrategy newInstance(String type){
        switch (type) {
            case "java":
                return new JavaJudgeStrategy();
            default:
                return new JavaJudgeStrategy();
        }
    }
}
