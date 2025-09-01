package com.desafio.analisecidades.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(String city) {
        super("Cidade não encontrada: " + city);
    }
}