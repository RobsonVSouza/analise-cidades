package com.desafio.analisecidades.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class WeatherServiceException extends RuntimeException {

    public WeatherServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public WeatherServiceException(String message) {
        super(message);
    }
}
