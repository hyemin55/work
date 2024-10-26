package com.example.org.error;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(ExecutionControl.UserException.class)
    public ResponseEntity<String> handleMyException(ExecutionControl.UserException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("error/my-error");
    }

}
