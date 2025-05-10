package com.lzy.oj.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzy.oj.bean.entity.Question;

import java.util.List;

public interface QuestionService {

    public Long addQuestion(Question question);

    public List<Question> listQuestion(Integer currentPage, Integer pageSize);

    public Long countQuestions();

    public Boolean deleteQuestion(Long id);

    public Question searchQuestionById(Long id);

    public Boolean updateCount(Integer submit, Integer ac, Long id);
}
