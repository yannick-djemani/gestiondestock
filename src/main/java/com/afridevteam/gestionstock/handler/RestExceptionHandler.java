package com.afridevteam.gestionstock.handler;

import com.afridevteam.gestionstock.exception.EntityNotFoundException;
import com.afridevteam.gestionstock.exception.InvalidEntityException;
import com.afridevteam.gestionstock.exception.InvalidOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> handleException(EntityNotFoundException ex, WebRequest webRequest) {
        final HttpStatus notFound = HttpStatus.NOT_FOUND;
        final ErrorDto errorDto = ErrorDto.builder()
                                          .code(ex.getErrorCodes())
                                          .httpCode(notFound.value())
                                          .message(ex.getMessage())
                                          .build();
        return new ResponseEntity<>(errorDto, notFound);
    }

    @ExceptionHandler(InvalidEntityException.class)
    public ResponseEntity<ErrorDto> handleException(InvalidEntityException ex, WebRequest webRequest) {
        final HttpStatus invalid = HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto = ErrorDto.builder()
                                          .code(ex.getErrorCodes())
                                          .httpCode(invalid.value())
                                          .message(ex.getMessage())
                                          .build();
        return new ResponseEntity<>(errorDto, invalid);

    }

    @ExceptionHandler(InvalidOperationException.class)
    public ResponseEntity<ErrorDto> handleException(InvalidOperationException ex, WebRequest webRequest) {
        final HttpStatus notFound = HttpStatus.BAD_REQUEST;
        final ErrorDto errorDto = ErrorDto.builder()
                                          .httpCode(notFound.value())
                                          .message(ex.getMessage())
                                          .build();
        return new ResponseEntity<>(errorDto, notFound);

    }

}
