package com.yousuf.best_route.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid port name")
public class InvalidPortNameException extends RuntimeException {
    public InvalidPortNameException(String message) {
        super(message);
    }
}
