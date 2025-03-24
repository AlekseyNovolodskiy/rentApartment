package com.first.function_module.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RentApartmentExceptionHandler {

    @ExceptionHandler(ApartmentException.class)
    public ResponseEntity<?> catchException(ApartmentException e){
        return ResponseEntity.status(e.getExceptionCode()).body(e.getMessage());

    }
    @ExceptionHandler(UserException.class)
    public ResponseEntity<?> catchException(UserException e){
        return ResponseEntity.ok(e.getMessage());

    }

    @ExceptionHandler(EmailSenderException.class)
    public ResponseEntity<?> catchException(EmailSenderException e){
        return ResponseEntity.ok(e.getMessage());

    }

}
