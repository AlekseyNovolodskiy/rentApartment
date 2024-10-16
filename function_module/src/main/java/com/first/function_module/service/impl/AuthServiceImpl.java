package com.first.function_module.service.impl;

import com.first.function_module.entity.UserInfoEntity;
import com.first.function_module.exception.UserException;
import com.first.function_module.model.dto.UserAuthDto;
import com.first.function_module.model.dto.UserInfoDto;
import com.first.function_module.repository.UserRepository;
import com.first.function_module.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.first.function_module.util.Base64EncodeDecode.decode;
import static com.first.function_module.util.Base64EncodeDecode.encode;
import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;



    @Override
    public String registrationUser(UserInfoDto userInfoDto) {

        UserInfoEntity userByNickname = userRepository.findUserInfoEntityByNickname(userInfoDto.getNickname());
        if (!isNull(userByNickname)) {
            throw new UserException("Данный пользователь уже существует");

        }
        UserInfoEntity userByLogin = userRepository.findUserInfoEntityByLogin(userInfoDto.getLogin());
        if (!isNull(userByLogin)) {
            return "Данный пользователь уже существует";
        }

        UserInfoEntity userInfoEntity = prepareUserSaveToBase(userInfoDto);
        userRepository.save(userInfoEntity);
        return "вы зарегистрированы";
    }

    @Override
    public String authorizationUser(UserAuthDto userAuthDto) {
        UserInfoEntity userByLogin = userRepository.findUserInfoEntityByLogin(userAuthDto.getLogin());
        if (isNull(userByLogin)) {
            return "Данного пользователя не существует";
        }
        if (!userAuthDto.getPassword().equals(decode(userByLogin.getPassword()))) {
            return "Неверный пароль";
        }

        String token = generateToken();
        userByLogin.setToken(token);
        userRepository.save(userByLogin);
        return token;
    }

    private String generateToken() {
        return UUID.randomUUID() + "|" + LocalDateTime.now().plusDays(1L);

    }

//todo метод перенести в mapstruckt
    private UserInfoEntity prepareUserSaveToBase(UserInfoDto userInfoDto) {


        return new UserInfoEntity(encode(userInfoDto.getPassword()), userInfoDto.getLogin(), userInfoDto.getNickname());
    }
}
