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
    public static final String TOKEN_APARTMENT_REGISTRATION_EXCEPTION = "Регистрация апартаментов доступна зарегистрированным пользователям";
    public static final String TOKEN_APARTMENT_BOOKING_EXCEPTION = "Аренда апартаментов доступна зарегистрированным пользователям";
    public static final String TOKEN_APARTMENT_COMMENT_EXCEPTION = "Оставлять коментарии могут только зарегистрированные пользователи";

    @Override
    public UserInfoEntity checkTokenForVAlid(String token,String apartmentMethod) {

        if(apartmentMethod.equals("registration")){
            return userRepository.findUserInfoEntityByToken(token)
                    .orElseThrow(() -> new UserException(TOKEN_APARTMENT_REGISTRATION_EXCEPTION));
        }
        if (apartmentMethod.equals("addComment")){
            return userRepository.findUserInfoEntityByToken(token)
                    .orElseThrow(() -> new UserException(TOKEN_APARTMENT_COMMENT_EXCEPTION));
        }
        if (apartmentMethod.equals("rentApartment")){
            return userRepository.findUserInfoEntityByToken(token)
                    .orElseThrow(() -> new UserException(TOKEN_APARTMENT_BOOKING_EXCEPTION));
        }

        return null;
    }

}
