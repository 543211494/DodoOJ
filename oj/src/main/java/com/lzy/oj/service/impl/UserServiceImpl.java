package com.lzy.oj.service.impl;

import com.lzy.oj.bean.entity.User;
import com.lzy.oj.bean.po.UserPO;
import com.lzy.oj.enums.ErrorEnum;
import com.lzy.oj.exception.BusinessException;
import com.lzy.oj.mapper.UserMapper;
import com.lzy.oj.service.UserService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;

@Repository
public class UserServiceImpl implements UserService {

    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "jzn";

    @Resource
    private UserMapper userMapper;

    @Override
    public Long register(User user) {
        try{
            UserPO userPO = UserPO.user2PO(user);
            userPO.setPassword(DigestUtils.md5DigestAsHex((SALT + userPO.getPassword()).getBytes()));
            userMapper.insert(userPO);
            return userPO.getId();
        }catch (DuplicateKeyException e){
            throw new BusinessException(ErrorEnum.REGISTER_ERROR);
        } catch (Exception e){
            throw e;
        }
    }
}
