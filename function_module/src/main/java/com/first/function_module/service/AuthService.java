package com.first.function_module.service;

import com.first.function_module.model.dto.UserAuthDto;
import com.first.function_module.model.dto.UserInfoDto;

import java.util.Map;


public interface AuthService {

    String registrationUser(UserInfoDto userInfoDto);

    String authorizationUser(UserAuthDto userAuthDto);

    String verifyMethodForLink(String login, String code);


}
