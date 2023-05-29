package com.tutorials.app.exception;
public class NoSuchTutorialExistsException extends RuntimeException{


    public NoSuchTutorialExistsException(String message) {
        super(message);
    }
}
