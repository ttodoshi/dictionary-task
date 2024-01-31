package org.example.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.example.dtos.error.ErrorResponseDto;
import org.example.exceptions.DictionaryNotFoundException;
import org.example.exceptions.WordAlreadyExistsException;
import org.example.exceptions.WordNotFoundException;
import org.example.exceptions.WordNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionAdvice {
    @ExceptionHandler({IllegalArgumentException.class, MethodArgumentNotValidException.class, HttpMessageNotReadableException.class, ConstraintViolationException.class})
    public ResponseEntity<ErrorResponseDto> handleInvalidRequestExceptions(HttpServletRequest request) {
        return new ResponseEntity<>(
                new ErrorResponseDto(
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        "Ошибка в поступивших данных",
                        request.getRequestURI()
                ),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(DictionaryNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleDictionaryNotFoundException(Exception e, HttpServletRequest request) {
        return new ResponseEntity<>(
                new ErrorResponseDto(
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        e.getMessage(),
                        request.getRequestURI()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(WordNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleWordNotFoundException(Exception e, HttpServletRequest request) {
        return new ResponseEntity<>(
                new ErrorResponseDto(
                        HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),
                        e.getMessage(),
                        request.getRequestURI()
                ),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(WordAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleWordAlreadyExistsException(Exception e, HttpServletRequest request) {
        return new ResponseEntity<>(
                new ErrorResponseDto(
                        HttpStatus.FORBIDDEN.value(),
                        HttpStatus.FORBIDDEN.getReasonPhrase(),
                        e.getMessage(),
                        request.getRequestURI()
                ),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(WordNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleWordNotValidException(Exception e, HttpServletRequest request) {
        return new ResponseEntity<>(
                new ErrorResponseDto(
                        HttpStatus.FORBIDDEN.value(),
                        HttpStatus.FORBIDDEN.getReasonPhrase(),
                        e.getMessage(),
                        request.getRequestURI()
                ),
                HttpStatus.FORBIDDEN
        );
    }
}
