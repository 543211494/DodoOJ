package com.lzy.oj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lzy.oj.bean.entity.Question;
import com.lzy.oj.bean.po.QuestionPO;
import com.lzy.oj.mapper.QuestionMapper;
import com.lzy.oj.service.QuestionService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<Question> listQuestion(Integer currentPage, Integer pageSize) {
        Page<QuestionPO> page = new Page<>(currentPage,pageSize);
        QueryWrapper<QuestionPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_delete",0);
        queryWrapper.orderByAsc("id");
        questionMapper.selectPage(page,queryWrapper);
        return page.getRecords().stream()
                .map(questionPO -> {
                    Question question = QuestionPO.po2Question(questionPO);
                    question.setAnswer(null);
                    question.setJudgeCase(null);
                    return question;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Long countQuestions() {
        return questionMapper.countAll();
    }

    @Override
    public Boolean deleteQuestion(Long id) {
        UpdateWrapper<QuestionPO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id",id);
        updateWrapper.set("is_delete",1);
        return  questionMapper.update(null,updateWrapper) > 0;
    }

    @Override
    public Question searchQuestionById(Long id) {
        QueryWrapper<QuestionPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        queryWrapper.eq("is_delete",0);
        List<QuestionPO> questionPOS = questionMapper.selectList(queryWrapper);
        if(questionPOS==null||questionPOS.size()==0){
            return null;
        }
        return QuestionPO.po2Question(questionPOS.get(0));
    }

    @Override
    public Boolean updateCount(Integer submit, Integer ac, Long id) {
        return questionMapper.updateCount(submit,ac,id) > 0;
    }
}
