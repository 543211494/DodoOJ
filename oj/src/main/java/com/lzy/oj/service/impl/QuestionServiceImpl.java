package com.lzy.oj.service.impl;

import com.lzy.oj.bean.entity.Question;
import com.lzy.oj.bean.po.QuestionPO;
import com.lzy.oj.mapper.QuestionMapper;
import com.lzy.oj.service.QuestionService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class QuestionServiceImpl implements QuestionService {

    @Resource
    private QuestionMapper questionMapper;

    @Override
    public Long addQuestion(Question question) {
        QuestionPO questionPO = QuestionPO.question2PO(question);
        questionMapper.insert(questionPO);
        return questionPO.getId();
    }
}
