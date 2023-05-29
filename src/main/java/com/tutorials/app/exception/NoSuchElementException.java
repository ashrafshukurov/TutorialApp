package com.tutorials.app.exception;



import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
public class NoSuchElementException extends RuntimeException{
    private final HttpStatus Status=HttpStatus.NOT_FOUND;
    public NoSuchElementException(String message) {
        super(message);
    }
}
