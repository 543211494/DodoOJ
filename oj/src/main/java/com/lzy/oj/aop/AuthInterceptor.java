package com.lzy.oj.aop;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lzy.oj.annotation.AuthCheck;
import com.lzy.oj.bean.dto.RequestDTO;
import com.lzy.oj.bean.entity.User;
import com.lzy.oj.enums.ErrorEnum;
import com.lzy.oj.enums.UserRoleEnum;
import com.lzy.oj.exception.BusinessException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private RedisTemplate redisTemplate;

    @Around("@annotation(authCheck)")
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        String role = authCheck.role();
        if(StringUtils.isNotBlank(role)){
            RequestDTO arg = (RequestDTO) joinPoint.getArgs()[0];
            if(arg.getToken()==null){
                throw new BusinessException(ErrorEnum.NO_LOGIN_ERROR);
            }
            String value = (String) redisTemplate.opsForValue().get(arg.getToken());
            System.out.println("value"+value);
            if(StringUtils.isBlank(value)){
                throw new BusinessException(ErrorEnum.NO_LOGIN_ERROR);
            }
            User user = JSON.parseObject(value, User.class);
            arg.setUser(user);
            UserRoleEnum userRole = UserRoleEnum.getEnumByRole(user.getRole());
            UserRoleEnum mustRole = UserRoleEnum.getEnumByRole(role);
            if(userRole.getLevel().intValue()<mustRole.getLevel().intValue()){
                throw new BusinessException(ErrorEnum.ROLE_ERROR);
            }
        }
        return joinPoint.proceed();
    }
}
