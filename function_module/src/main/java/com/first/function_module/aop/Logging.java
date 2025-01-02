package com.first.function_module.aop;

import com.first.function_module.entity.LogInfoEntity;
import com.first.function_module.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class Logging {
    public static final String POINT_CUT_CONST = "execution(* com.first.function_module.service.impl.AuthServiceImpl.authorizationUser(..))";

    private final LogRepository logRepository;

    //Логирование аутентификации
    @AfterReturning(pointcut = POINT_CUT_CONST, returning = "result")
    public void logUserAuth(Object result) {

        String resultString = result.toString();
        log.info("Лог добавлен");
        LogInfoEntity logInfoEntity = new LogInfoEntity();

        logInfoEntity.setMessage(resultString+" aspect");
        logInfoEntity.setTime_of_logging(LocalDateTime.now());

        logRepository.save(logInfoEntity);
    }

}
