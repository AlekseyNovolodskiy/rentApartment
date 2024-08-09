package com.first.function_module.scheduler;

import com.first.function_module.entity.UserInfoEntity;
import com.first.function_module.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@EnableScheduling
@RequiredArgsConstructor
public class AuthScheduler {

    private final UserRepository userRepository;

    @Scheduled(fixedDelay = 600000)
    private void checkValidToken (){
        log.info("планировщик актуальности токена начал работу");
        List<UserInfoEntity> users = userRepository.findUserInfoEntitiesByTokenIsNotNull();
        for (UserInfoEntity user : users) {
            log.info("проверка токена пользователя "+ user.getNickname());
            String token = user.getToken();
            LocalDateTime tokeTime = parseTokenInfo(token);
            if(checkDate(tokeTime)){
                log.info("токен пользователя "+ user.getNickname()+ " был удален");
                user.setToken(null);
                userRepository.save(user);
            }
            log.info("планировщик закончил свою работу");

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
