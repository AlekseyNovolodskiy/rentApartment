package com.first.function_module.model.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class UserInfoDto {
    private String password;
    private String login;
    private String  nickname;
}
