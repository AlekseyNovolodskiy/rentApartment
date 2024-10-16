package com.first.function_module.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MetricsCalc {

public static final String POINT_CUT_CONST= "execution(* com.first.function_module.service.impl.AuthServiceImpl.authorizationUser(..))";

    @AfterReturning (pointcut = POINT_CUT_CONST,returning = "result")
    public void catchUsersLogin(JoinPoint joinPoint, Object result){
        String name = joinPoint.getSignature().getName();
        String string = result.toString();
        log.info("аспект отработал");
    }

}

