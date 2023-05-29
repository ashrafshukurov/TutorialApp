package com.tutorials.app.exception;


import com.tutorials.app.dto.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> noSuchExceptionHandler(NoSuchElementException ex){
        log.info("there is not such resource in the Db Table");
        ErrorMessage errorMessage=new ErrorMessage();
        errorMessage.setErrorCode(ex.getStatus().value());
        errorMessage.setNow(LocalDateTime.now());
        errorMessage.setHttpStatus(ex.getStatus());
        errorMessage.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(errorMessage);
    }
    @ExceptionHandler(NoSuchTutorialExistsException.class)
    public ResponseEntity<?> noSuchCustomer(NoSuchTutorialExistsException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    @ExceptionHandler(TutorialAlreadyExistsException.class)
    private ResponseEntity<?> tutorialExists(TutorialAlreadyExistsException ex){
        ErrorMessage errorMessage=new ErrorMessage();
        errorMessage.setHttpStatus(ex.getHttpStatus());
        errorMessage.setErrorMessage(ex.getMessage());
        errorMessage.setNow(LocalDateTime.now());
        errorMessage.setErrorCode(ex.getHttpStatus().value());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);

    }



}
