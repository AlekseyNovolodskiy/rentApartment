package com.first.function_module.service;

import com.first.function_module.entity.UserInfoEntity;

public interface CheckValidTokenService {
    public UserInfoEntity checkTokenForVAlid(String token);
}
