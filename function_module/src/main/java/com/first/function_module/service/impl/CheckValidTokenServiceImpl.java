package com.first.function_module.service.impl;

import com.first.function_module.entity.ApartmentEntity;
import com.first.function_module.entity.UserInfoEntity;
import com.first.function_module.exception.ApartmentException;
import com.first.function_module.exception.UserException;
import com.first.function_module.repository.UserRepository;
import com.first.function_module.service.CheckValidTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CheckValidTokenServiceImpl implements CheckValidTokenService {
    private final UserRepository userRepository;
    public static final String TOKEN_MESSAGE_EXCEPTION = "Регистрация Апартаментов доступна зарегистрированным пользователям";

    @Override
    public UserInfoEntity checkTokenForVAlid(String token) {
        return userRepository.findUserInfoEntityByToken(token)
                .orElseThrow(() -> new UserException(TOKEN_MESSAGE_EXCEPTION));
    }

}
