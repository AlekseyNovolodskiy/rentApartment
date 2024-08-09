package com.first.function_module.service;

import com.first.function_module.model.dto.UserAuthDto;
import com.first.function_module.model.dto.UserInfoDto;


public interface AuthService {
    public String registrationUser (UserInfoDto userInfoDto);

    public  String authorizationUser(UserAuthDto userAuthDto);



}
