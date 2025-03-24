package com.first.function_module.model.dto;

import lombok.Data;

@Data
public class UserRequestDto {
    private String email;
    private String login;
    private String code;
}
