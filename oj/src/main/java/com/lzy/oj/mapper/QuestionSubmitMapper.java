package com.lzy.oj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzy.oj.bean.po.QuestionSubmitPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface QuestionSubmitMapper extends BaseMapper<QuestionSubmitPO> {
}
