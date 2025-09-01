package com.desafio.analisecidades.controller;

import com.desafio.analisecidades.response.tempo.TemperaturaDto;
import com.desafio.analisecidades.response.tempo.WeatherResponse;
import com.desafio.analisecidades.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clima" )
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Operation(summary = "Buscar temperatura atual de uma cidade")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Temperatura encontrada"),
            @ApiResponse(responseCode = "404", description = "Cidade não encontrada")
    })
    @GetMapping("/{city}/temperatura")
    public ResponseEntity<TemperaturaDto> getTemperatura(@PathVariable String city) {
        TemperaturaDto dto = weatherService.getTemperaturaAtualPorCidade(city);
        return ResponseEntity.ok(dto);
    }
}
