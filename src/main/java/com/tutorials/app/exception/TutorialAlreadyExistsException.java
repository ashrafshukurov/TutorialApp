package com.tutorials.app.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TutorialAlreadyExistsException extends RuntimeException{
    private final HttpStatus httpStatus=HttpStatus.CONFLICT;

    public TutorialAlreadyExistsException(String message) {
        super(message);
    }
}
