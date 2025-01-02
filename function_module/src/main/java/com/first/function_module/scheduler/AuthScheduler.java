package com.first.function_module.scheduler;

import com.first.function_module.entity.UserInfoEntity;
import com.first.function_module.repository.UserRepository;
import com.first.function_module.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@EnableScheduling
@RequiredArgsConstructor
public class AuthScheduler {

    private Logger logger = LoggerFactory.getLogger(AuthScheduler.class);
    private final UserRepository userRepository;


    @Scheduled(fixedDelay = 60000)
    private void checkValidToken (){
        logger.info("планировщик актуальности токена начал работу");
        List<UserInfoEntity> users = userRepository.findUserInfoEntitiesByTokenIsNotNull();
        for (UserInfoEntity user : users) {
            logger.info("проверка токена пользователя "+ user.getNickname());
            String token = user.getToken();
            LocalDateTime tokenTime = parseTokenInfo(token);
            if(checkDate(tokenTime)){
                logger.info("токен пользователя "+ user.getNickname()+ " был удален");
                user.setToken(null);
                userRepository.save(user);
            }
            logger.info("планировщик закончил свою работу");

        }
    }
    private LocalDateTime parseTokenInfo (String token){
        int i = token.indexOf("|")+1;
        String substring = token.substring(i);
        LocalDateTime tokenTime = LocalDateTime.parse(substring);
        return  tokenTime;
    }
    private boolean checkDate (LocalDateTime time){
        return LocalDateTime.now().isAfter(time);
    }
}
