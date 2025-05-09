package com.lzy.oj.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lzy.oj.bean.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserPO> {
}
