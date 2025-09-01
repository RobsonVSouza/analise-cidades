package com.desafio.analisecidades.controller;

import com.desafio.analisecidades.response.AnaliseEstadoResponse;
import com.desafio.analisecidades.service.AnaliseService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analise")
public class AnaliseController {

    private final AnaliseService analiseService;

    public AnaliseController(AnaliseService analiseService) {
        this.analiseService = analiseService;
    }

    @GetMapping("/estado")
    @Operation(
            summary = "Analisa estado",
            description = "Retorna análise do estado, cidade mais quente e mais fria"
    )
    public AnaliseEstadoResponse analisarEstado(@RequestParam String sigla) {
        return analiseService.analisarEstado(sigla);
    }
}
