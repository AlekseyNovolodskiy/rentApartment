package com.first.function_module.exception;

import lombok.Data;

import java.util.function.Supplier;

@Data
public class UserException extends RuntimeException  {
    private int exceptionCode;

//    public UserException(String message, int exceptionCode) {
//        super(message);
//        this.exceptionCode = exceptionCode;
//    }


    public UserException(String message) {
        super(message);

    }

}


