package com.first.function_module.service.impl;

import com.first.function_module.entity.UserInfoEntity;
import com.first.function_module.exception.UserException;
import com.first.function_module.model.dto.UserAuthDto;
import com.first.function_module.model.dto.UserInfoDto;
import com.first.function_module.repository.UserRepository;
import com.first.function_module.service.AuthService;
import com.first.function_module.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.first.function_module.util.Base64EncodeDecode.decode;
import static com.first.function_module.util.Base64EncodeDecode.encode;
import static java.util.Objects.isNull;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final EmailService emailService;

    private final static String TEXT_TO_VERIFICATION = "уважаемый пользователь потвердите свой емейл \n " +
            "перейдите по ссылке \n";
    private final static String  SUBJEXT_OF_EMAIL_TO_VERIFICATION = "потвердите ваш емейл";


    @Override
    public String registrationUser(UserInfoDto userInfoDto) {

        UserInfoEntity userByNickname = userRepository.findUserInfoEntityByNickname(userInfoDto.getNickname());
        if (!isNull(userByNickname)) {
            throw new UserException("Данный пользователь уже существует");

        }
        UserInfoEntity userByLogin = userRepository.findUserInfoEntitiesByEmail(userInfoDto.getEmail());
        if (!isNull(userByLogin)) {
            return "Данный пользователь уже существует";
        }

        UserInfoEntity userInfoEntity = new UserInfoEntity();

        userInfoEntity.setNickname(userInfoDto.getNickname());
        userInfoEntity.setLogin(userInfoDto.getLogin());
        userInfoEntity.setPassword(encode(userInfoDto.getPassword()));
        userInfoEntity.setEmail(userInfoDto.getEmail());
        userInfoEntity.setDateRegistration(LocalDateTime.now());
        userInfoEntity.setVerification(generateVerificationNumber());
        userRepository.save(userInfoEntity);
        return "вы зарегистрированы";
    }


    @Override
    public String authorizationUser(UserAuthDto userAuthDto) {
        UserInfoEntity userByLogin = userRepository.findUserInfoEntitiesByEmail(userAuthDto.getEmail());
        if (isNull(userByLogin)) {
            return "Данного пользователя не существует";
        }
        if (!userAuthDto.getPassword().equals(decode(userByLogin.getPassword()))) {
            return "Неверный пароль";
        }


        UserInfoEntity userAuth = userRepository.findUserInfoEntitiesByEmail(userAuthDto.getEmail());

        if (!userAuth.getVerification().equals("verified")) {
            emailService.emailVerification(userAuth.getEmail(),
                    SUBJEXT_OF_EMAIL_TO_VERIFICATION,
                    TEXT_TO_VERIFICATION + urlGenerator(userAuth.getLogin(),userAuth.getVerification()));
        }
        if (!userAuth.getVerification().equals("verified")) {
            log.info("Проверьте свой емейл для потверждения аккаунта");
        }


        String token = generateToken();
        userByLogin.setToken(token);
        userRepository.save(userByLogin);
        return token;
    }

    @Override
    public String checkEmail(String verifNumber) {


        return "";
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

    private String urlGenerator(String login,String stringCode) {

        return "http://localhost:9096/verify?login="+login+"&code=" + stringCode;
    }

    @Override
    public String verifyMethodForLink(Map<String, String> responceMap) {

        String code = responceMap.get("code");
        String login = responceMap.get("login");
        UserInfoEntity userVerify = userRepository.findUserByLoginAndVerificationNumber(login,code);
        if(!isNull(userVerify)){
            userVerify.setVerification("verified");
            userRepository.save(userVerify);
        }
        else return "Пользователь не найден";

        return "Пользователь верифицирован";
    }
}

//todo метод перенести в mapstruckt
//    private UserInfoEntity prepareUserSaveToBase(UserInfoDto userInfoDto) {
//
//
//        return new UserInfoEntity(encode(userInfoDto.getPassword()), userInfoDto.getLogin(), userInfoDto.getNickname(), userInfoDto.getEmail(), generateVerificationNumber());
//    }

