package com.first.function_module.service.impl;

import com.first.function_module.entity.UserInfoEntity;
import com.first.function_module.exception.UserException;
import com.first.function_module.model.dto.UserAuthDto;
import com.first.function_module.model.dto.UserInfoDto;
import com.first.function_module.repository.UserRepository;
import com.first.function_module.service.AuthService;
import com.first.function_module.service.integrated.IntegrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.first.function_module.util.Base64EncodeDecode.decode;
import static com.first.function_module.util.Base64EncodeDecode.encode;
import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final UserRepository userRepository;
    private final IntegrationService integrationService;
    private final String EMAIL_SENDER_IS_UNACTIVE = "отсутствует доступ к рассылке";
    public static final String USER_EXIST_EXEPTION = "Данный пользователь уже существует";
    public static final String YOU_ARE_REGISTERD = "вы зарегистрированы";
    public static final String USER_NO_EXIST_EXEPTION = "Данного пользователя не существует";
    public static final String WRONG_PASSWORD = "Неверный пароль";
    public static final String USER_NOT_FOUND = "Пользователь не найден";
    public static final String USER_VERIFIED = "Пользователь верифицирован";





    @Override
    public String registrationUser(UserInfoDto userInfoDto) {


        UserInfoEntity userByNickname = userRepository.findUserInfoEntityByNickname(userInfoDto.getNickname());
        if (!isNull(userByNickname)) {
            logger.error(USER_EXIST_EXEPTION);
            throw new UserException(USER_EXIST_EXEPTION);

        }
        UserInfoEntity userByLogin = userRepository.findUserInfoEntitiesByEmail(userInfoDto.getEmail());
        if (!isNull(userByLogin)) {
            logger.error(USER_EXIST_EXEPTION);
            return USER_EXIST_EXEPTION;
        }

        UserInfoEntity userInfoEntity = new UserInfoEntity();

        userInfoEntity.setNickname(userInfoDto.getNickname());
        userInfoEntity.setLogin(userInfoDto.getLogin());
        userInfoEntity.setPassword(encode(userInfoDto.getPassword()));
        userInfoEntity.setEmail(userInfoDto.getEmail());
        userInfoEntity.setDateRegistration(LocalDateTime.now());
        userInfoEntity.setVerification(generateVerificationNumber());
        userRepository.save(userInfoEntity);
        logger.info(YOU_ARE_REGISTERD);
        return YOU_ARE_REGISTERD;
    }


    @Override
    public String authorizationUser(UserAuthDto userAuthDto) {
        UserInfoEntity userByLogin = userRepository.findUserInfoEntitiesByEmail(userAuthDto.getEmail());
        if (isNull(userByLogin)) {
            return USER_NO_EXIST_EXEPTION;
        }
        if (!userAuthDto.getPassword().equals(decode(userByLogin.getPassword()))) {
            return WRONG_PASSWORD;
        }

        String token = generateToken();
        userByLogin.setToken(token);
        userRepository.save(userByLogin);

        if (!userByLogin.getVerification().equals("verified")) {

            try {
                integrationService.getIntegrationWithEmail(userByLogin);
            } catch (Exception e) {
                throw new UserException(EMAIL_SENDER_IS_UNACTIVE);
            }
            return "Проверьте свой емейл для потверждения аккаунта";
        }

        return token;
    }



    private String generateToken() {
        return UUID.randomUUID() + "|" + LocalDateTime.now().plusDays(1L);
    }

    private static String generateVerificationNumber() {
        int x = 0;
        StringBuilder verifNuber = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            x = (int) Math.round(Math.random() * 10);
            verifNuber.append(x);
        }
        return verifNuber.toString();
    }


    @Override
    public String verifyMethodForLink(String login, String code) {

        UserInfoEntity userVerify = userRepository.findUserByLoginAndVerificationNumber(login, code);
        if (!isNull(userVerify)) {
            userVerify.setVerification("verified");
            userRepository.save(userVerify);
        } else return USER_NOT_FOUND;

        return USER_VERIFIED;
    }


}


