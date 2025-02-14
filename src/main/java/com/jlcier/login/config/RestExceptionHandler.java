package com.jlcier.login.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    private ResponseEntity<String> exceptionHandler(Exception exception) {
        exception.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("500 Internal Server Error");
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    private ResponseEntity<?> authenticationFalueHandler(InternalAuthenticationServiceException exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Username/Password invalid");
    }
}
