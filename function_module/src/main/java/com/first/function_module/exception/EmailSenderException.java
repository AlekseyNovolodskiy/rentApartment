package com.first.function_module.exception;

import lombok.Data;

@Data
public class EmailSenderException extends RuntimeException  {
    private int exceptionCode;

//    public UserException(String message, int exceptionCode) {
//        super(message);
//        this.exceptionCode = exceptionCode;
//    }


    public EmailSenderException(String message) {
        super(message);

    }

}


