package com.lzy.oj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzy.oj.bean.po.QuestionPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface QuestionMapper extends BaseMapper<QuestionPO> {

    @Select("SELECT COUNT(*) FROM question WHERE is_delete = 0;")
    Long countAll();

    @Update("UPDATE question " +
            "SET submit_count = submit_count + #{submit}, accepted_count = accepted_count + #{ac} " +
            "WHERE id = #{id}")
    Long updateCount(@Param("submit")Integer submit,@Param("ac")Integer ac,@Param("id")Long id);
}
