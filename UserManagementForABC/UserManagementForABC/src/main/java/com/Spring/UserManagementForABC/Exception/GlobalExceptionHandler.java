package com.Spring.UserManagementForABC.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(SystemException.class)
    public ResponseEntity<?> handleDemoException(SystemException ex) {
        HttpStatus status = switch (ex.getErrorCode()) {
            case ENTITY_NOT_FOUND, USER_NOT_FOUND -> HttpStatus.NOT_FOUND;
            case EMAIL_ALREADY_IN_USE -> HttpStatus.ALREADY_REPORTED;
            case ROLE_NOT_FOUND -> HttpStatus.NOT_FOUND;
            case PERMISSION_NOT_FOUND -> HttpStatus.NOT_FOUND;
            case TOKEN_NOT_FOUND, TOKEN_EXPIRED, TOKEN_NOT_VALID, TOKEN_TYPE_MISMATCH, INVALID_CREDENTIALS, LOGIN_FAILED, NOT_AUTHENTICATED -> HttpStatus.UNAUTHORIZED;
        };

        return new ResponseEntity<>(status);
    }
}

