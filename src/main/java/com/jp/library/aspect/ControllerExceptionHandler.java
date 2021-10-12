package com.jp.library.aspect;

import com.jp.library.exception.BadRequestException;
import com.jp.library.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity badRequestExceptionHandler(BadRequestException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return ResponseEntity.badRequest().body(new Object());
    }

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity notFoundExceptionHandler(NotFoundException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return ResponseEntity.notFound().build();
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity applicationExceptionHandler(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return ResponseEntity.internalServerError().build();
    }
}
