package com.first.function_module.service;

import com.first.function_module.model.dto.UserAuthDto;
import com.first.function_module.model.dto.UserInfoDto;

import java.util.Map;


public interface AuthService {
    public String registrationUser (UserInfoDto userInfoDto);

    public  String authorizationUser(UserAuthDto userAuthDto);

    String checkEmail (String verifNumber);

    String verifyMethodForLink(Map<String,String> responceMap);



}
