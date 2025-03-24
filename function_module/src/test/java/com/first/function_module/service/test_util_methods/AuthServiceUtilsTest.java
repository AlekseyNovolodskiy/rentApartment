package com.first.function_module.service.test_util_methods;

import com.first.function_module.entity.UserInfoEntity;
import com.first.function_module.model.dto.UserInfoDto;

import java.time.LocalDateTime;

public class AuthServiceUtilsTest {
    public static UserInfoDto getUserInfoMethod() {
        UserInfoDto userInfoDto = new UserInfoDto();

        userInfoDto.setPassword("password");
        userInfoDto.setNickname("NickName");
        userInfoDto.setLogin("Login");
        userInfoDto.setEmail("email");
        return userInfoDto;
    }


    public static UserInfoEntity getUserEntityMethodWithUser() {
        UserInfoEntity userInfoEntity = new UserInfoEntity
                (1L,
                        LocalDateTime.parse("2025-10-05T11:59:11.332000"),
                        "cGFzc3dvcmQ="
                        , "Login"
                        , "email"
                        , "NickName"
                        , "83f887bb-eda8-409e-9231-aaaabf36d404|2034-07-10T12:48:11.796983900"
                        , "verified");
        return userInfoEntity;
    }
    public static UserInfoDto getUserMethodWithOutFoundedUser() {
        UserInfoDto userInfoDto = new UserInfoDto();

        userInfoDto.setPassword("password2");
        userInfoDto.setNickname("NickName2");
        userInfoDto.setLogin("Login2");
        userInfoDto.setEmail("email2");
        return userInfoDto;
    }
}
