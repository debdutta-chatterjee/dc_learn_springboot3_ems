package org.skg.emsbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(TodoAPIException.class)
    public ResponseEntity<ErroDetails> exceptionHandler(TodoAPIException exception, WebRequest webRequest)
    {
        ErroDetails erroDetails = new ErroDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false)
        );

        return new ResponseEntity<ErroDetails>(erroDetails, HttpStatus.BAD_REQUEST);
    }
}
