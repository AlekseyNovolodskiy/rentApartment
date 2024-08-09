package com.first.function_module.controller;
import com.first.function_module.model.dto.UserAuthDto;
import com.first.function_module.model.dto.UserInfoDto;
import com.first.function_module.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.first.function_module.controller.ControllerConstants.AUTHORIZATION;
import static com.first.function_module.controller.ControllerConstants.REGISTRATION;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(REGISTRATION)
    public String registrationUser(@RequestBody UserInfoDto userInfoDto) {

        return authService.registrationUser(userInfoDto);
    }

    @PostMapping(AUTHORIZATION)
    public String   authorizationUser (@RequestBody UserAuthDto userAuthDto){

        return authService.authorizationUser(userAuthDto);
    }


}
