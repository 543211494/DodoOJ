package com.lzy.oj.judge.strategy;

import com.lzy.oj.judge.strategy.impl.DefaultJudgeStrategy;

public class JudgeStrategyFactory {

    public static JudgeStrategy newInstance(String type){
        switch (type) {
            case "java":
                return new DefaultJudgeStrategy();
            default:
                return new DefaultJudgeStrategy();
        }
    }
}
