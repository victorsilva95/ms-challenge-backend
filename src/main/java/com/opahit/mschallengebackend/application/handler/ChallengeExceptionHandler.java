package com.opahit.mschallengebackend.application.handler;

import com.opahit.mschallengebackend.application.response.error.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ChallengeExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException exception) {
        List<String> errorMessages = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> errorMessages.add(fieldError.getDefaultMessage()));
        ApiError apiError = new ApiError(LocalDateTime.now(),
                String.valueOf(HttpStatus.BAD_REQUEST.value()),
                "Requisição inválida",
                errorMessages, request.getRequestURL().toString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
        return new ResponseEntity<>(apiError, headers, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiError> handleConstraintViolationException(HttpServletRequest request, ConstraintViolationException exception) {
        List<String> errorMessages = new ArrayList<>();
        exception.getConstraintViolations().forEach(constraintViolation -> errorMessages.add(constraintViolation.getMessage()));
        ApiError apiError = new ApiError(LocalDateTime.now(),
                String.valueOf(HttpStatus.BAD_REQUEST.value()),
                "Requisição inválida",
                errorMessages,
                request.getRequestURL().toString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
        return new ResponseEntity<>(apiError, headers, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(HttpServletRequest request, Exception exception) {
        ApiError apiError = new ApiError(LocalDateTime.now(),
                String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                "Erro inesperado - "+ exception.getMessage()
                , request.getRequestURL().toString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PROBLEM_JSON);
        return new ResponseEntity<>(apiError, headers, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
