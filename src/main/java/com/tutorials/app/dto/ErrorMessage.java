package com.tutorials.app.dto;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorMessage {
    private HttpStatus httpStatus;
    private LocalDateTime now;
    private String errorMessage;
    private int errorCode;


}
