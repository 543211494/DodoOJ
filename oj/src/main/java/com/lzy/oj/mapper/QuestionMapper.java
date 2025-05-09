package com.lzy.oj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzy.oj.bean.po.QuestionPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface QuestionMapper extends BaseMapper<QuestionPO> {

    @Select("SELECT COUNT(*) FROM question WHERE is_delete = 0;")
    Long countAll();
}
