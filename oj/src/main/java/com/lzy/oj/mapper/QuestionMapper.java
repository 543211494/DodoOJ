package com.lzy.oj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzy.oj.bean.po.QuestionPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface QuestionMapper extends BaseMapper<QuestionPO> {
}
