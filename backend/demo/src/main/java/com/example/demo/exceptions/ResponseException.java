package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResponseException extends ResponseStatusException {
    private String message;

    public ResponseException(HttpStatus status) {
        super(status);
    }

    public ResponseException(HttpStatus status, String message) {
        super(status, message);
        this.message = message;
    }

    public static void nullHandler(Object o, String message) {
        if (o == null) throw new ResponseException(HttpStatus.BAD_REQUEST,message);
    }
}
