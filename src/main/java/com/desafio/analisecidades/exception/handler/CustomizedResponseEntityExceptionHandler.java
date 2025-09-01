package com.desafio.analisecidades.exception.handler;

import com.desafio.analisecidades.exception.CityNotFoundException;
import com.desafio.analisecidades.exception.ExceptionsResponse;
import com.desafio.analisecidades.exception.ProductNotFoundException;
import com.desafio.analisecidades.exception.WeatherServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionsResponse> handleAllExceptions(
            Exception ex, WebRequest request) {
        ExceptionsResponse exceptionsResponse = new ExceptionsResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public final ResponseEntity<ExceptionsResponse> handleBadRequestExceptions(
            Exception ex, WebRequest request) {
        ExceptionsResponse exceptionsResponse = new ExceptionsResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionsResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CityNotFoundException.class)
    public final ResponseEntity<ExceptionsResponse> handleCityNotFound(
            CityNotFoundException ex, WebRequest request) {
        ExceptionsResponse body = new ExceptionsResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WeatherServiceException.class)
    public final ResponseEntity<ExceptionsResponse> handleWeatherServiceExceptions(
            WeatherServiceException ex, WebRequest request) {
        ExceptionsResponse exceptionsResponse = new ExceptionsResponse(
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionsResponse, HttpStatus.BAD_GATEWAY);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        StringBuilder errors = new StringBuilder("Validation failed for fields: ");
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.append(String.format("%s (%s); ", error.getField(), error.getDefaultMessage()));
        }

        ExceptionsResponse exceptionsResponse = new ExceptionsResponse(
                new Date(),
                errors.toString(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionsResponse, HttpStatus.BAD_REQUEST);
    }
}
