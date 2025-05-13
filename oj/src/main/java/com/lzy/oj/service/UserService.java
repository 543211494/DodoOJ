package com.lzy.oj.service;

import com.lzy.oj.bean.entity.User;

public interface UserService {

    public Long register(User user);

    public User searchUserByAccount(String account);

    public User examAccount(String account,String password);

    public Boolean updateUser(User user);
}
