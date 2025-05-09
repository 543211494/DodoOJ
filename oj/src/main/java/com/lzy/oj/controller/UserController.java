package com.lzy.oj.controller;

import com.lzy.oj.bean.dto.ResponseDTO;
import com.lzy.oj.bean.dto.user.RegisterDTO;
import com.lzy.oj.bean.entity.User;
import com.lzy.oj.enums.ErrorEnum;
import com.lzy.oj.exception.BusinessException;
import com.lzy.oj.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseDTO<Long> register(@RequestBody RegisterDTO registerDTO){
        User user = new User();
        if(!registerDTO.getPassword().equals(registerDTO.getCheckPassword())){
            throw new BusinessException(ErrorEnum.PASSWORD_NOT_EQUAL_ERROR);
        }
        user.setAccount(registerDTO.getAccount());
        user.setPassword(registerDTO.getPassword());
        return ResponseDTO.success(userService.register(user));
    }
}
