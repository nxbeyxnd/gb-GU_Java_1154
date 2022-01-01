package ru.gb.mall.inventory.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.gb.mall.inventory.exception.DuplicatedValueException;
import ru.gb.mall.inventory.exception.EntityNotFoundException;
import ru.gb.mall.inventory.mail.MailSenderException;

import java.util.Date;

@RestControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ApiError> handleNotFound(EntityNotFoundException ex) {
        return new ResponseEntity<>(
                new ApiError(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage(),
                        new Date()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({DuplicatedValueException.class})
    public ResponseEntity<ApiError> handleNotFound(DuplicatedValueException ex) {
        return new ResponseEntity<>(
                new ApiError(
                        HttpStatus.BAD_REQUEST.value(),
                        ex.getMessage(),
                        new Date()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MailSenderException.class})
    public ResponseEntity<ApiError> handleMailException(MailSenderException ex) {
        return new ResponseEntity<>(new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                new Date()
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
