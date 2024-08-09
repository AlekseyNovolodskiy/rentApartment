package com.first.function_module.exception;

import lombok.Data;

@Data
public class ApartmentException extends RuntimeException{

    private int exceptionCode;

    public ApartmentException(String message, int exceptionCode) {
        super(message);
        this.exceptionCode = exceptionCode;
    }
}
