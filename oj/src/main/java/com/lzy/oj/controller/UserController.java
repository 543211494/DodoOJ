package com.lzy.oj.controller;

import com.alibaba.fastjson.JSON;
import com.lzy.oj.annotation.AuthCheck;
import com.lzy.oj.bean.dto.ResponseDTO;
import com.lzy.oj.bean.dto.user.*;
import com.lzy.oj.bean.entity.User;
import com.lzy.oj.enums.ErrorEnum;
import com.lzy.oj.constant.UserRoleConstant;
import com.lzy.oj.exception.BusinessException;
import com.lzy.oj.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate redisTemplate;

    public final static String TOKEN_PREFIX = "user-token-";

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseDTO<Long> register(@RequestBody RegisterDTO registerDTO){
        User user = new User();
        if(!registerDTO.validate()){
            throw new BusinessException(ErrorEnum.PARAM_ERROR);
        }
        if(!registerDTO.getPassword().equals(registerDTO.getCheckPassword())){
            throw new BusinessException(ErrorEnum.PASSWORD_NOT_EQUAL_ERROR);
        }
        user.setAccount(registerDTO.getAccount());
        user.setPassword(registerDTO.getPassword());
        user.setRole(UserRoleConstant.USER);
        user.setUserName("user");
        return ResponseDTO.success(userService.register(user));
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseDTO<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO){
        User user = userService.examAccount(loginDTO.getAccount(),loginDTO.getPassword());
        if(user!=null){
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
            user.setPassword(null);
            loginResponseDTO.setUser(user);
            String prefix = TOKEN_PREFIX+user.getId();
            Set<String> keys = redisTemplate.keys(prefix+"*");
            if(keys.size()>=3){
                loginResponseDTO.setToken((String) keys.toArray()[0]);
            }else{
                String token = prefix + "-" + UUID.randomUUID().toString();
                redisTemplate.opsForValue().set(token, JSON.toJSONString(user),1, TimeUnit.HOURS);
                loginResponseDTO.setToken(token);
            }
            return ResponseDTO.success(loginResponseDTO);
        }else{
            throw new BusinessException(ErrorEnum.LOGIN_ERROR);
        }
    }

    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    @AuthCheck(role = UserRoleConstant.USER)
    public ResponseDTO<Boolean> logout(@RequestBody LogoutDTO logoutDTO){
        redisTemplate.delete(logoutDTO.getToken());
        return ResponseDTO.success(true);
    }

    @RequestMapping(value = "/updateInfo",method = RequestMethod.POST)
    @AuthCheck(role = UserRoleConstant.USER)
    public ResponseDTO<User> updateUser(@RequestBody UpdateDTO updateDTO){
        if(!updateDTO.validate()){
            throw new BusinessException(ErrorEnum.PARAM_ERROR);
        }
        User user = updateDTO.getUser();
        user.setUserName(updateDTO.getUserName());
        user.setEmail(updateDTO.getEmail());
        userService.updateUser(user);
        String prefix = TOKEN_PREFIX+user.getId();
        Set<String> keys = redisTemplate.keys(prefix+"*");
        for(String key : keys){
            redisTemplate.opsForValue().set(key, JSON.toJSONString(user),1, TimeUnit.HOURS);
        }
        return ResponseDTO.success(user);
    }
}
