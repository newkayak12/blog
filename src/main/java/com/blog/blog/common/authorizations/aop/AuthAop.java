package com.blog.blog.common.authorizations.aop;

import com.blog.blog.common.authorizations.tokenManager.TokenManager;
import com.blog.blog.common.constants.Constants;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.lang.reflect.Parameter;
import java.util.Objects;

@Component
@Aspect
public class AuthAop {
    TokenManager tokenManager = new TokenManager();
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Around("@annotation(com.blog.blog.common.authorizations.annotation.Auth)")
    public Object decrypt(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        Object[] parameterValue = proceedingJoinPoint.getArgs();
        Parameter[] parameters = methodSignature.getMethod().getParameters();
        for(int i = 0; i<parameters.length; i++){
            if(parameters[i].getName().equals("authorization")){
                if(Objects.isNull(parameterValue[i])){
                    throw new SecurityException("잘못된 접근입니다.");
                }
                parameterValue[i] = tokenManager.decrypt((String)parameterValue[i], Constants.SALT_VALUE);
                break;
            }
        }

        return proceedingJoinPoint.proceed(proceedingJoinPoint.getArgs());
    }
}
