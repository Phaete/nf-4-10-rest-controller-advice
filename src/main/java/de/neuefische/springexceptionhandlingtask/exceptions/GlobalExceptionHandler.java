package de.neuefische.springexceptionhandlingtask.exceptions;

import de.neuefische.springexceptionhandlingtask.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorMessage noSuchElementExceptionHandler(NoSuchElementException e) {
        return new ErrorMessage(e.getMessage(), Instant.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    ErrorMessage noSuchElementExceptionHandler(Exception e) {
        return new ErrorMessage(e.getMessage(), Instant.now());
    }
}
