package com.example.backend.configure;

import com.example.backend.exception.LoginException;
import com.example.backend.exception.MessageArgumentException;
import com.example.backend.utils.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(LoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result<String> handleException(LoginException exception) {
        String message = exception.getMessage();
        return Result.Err(message);
    }

    @ExceptionHandler(MessageArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<String> handleException(MessageArgumentException exception) {
        String message = exception.getMessage();
        return Result.Err(message);
    }
}
