package com.first.function_module.controller;

import com.first.function_module.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VerifyController {

    private final AuthService authService;

    @GetMapping("/verify")
    public String getVerifCode(@RequestParam String login, @RequestParam String code){
        authService.verifyMethodForLink(login,code);
        return "емейл потвержден";
    }
}
