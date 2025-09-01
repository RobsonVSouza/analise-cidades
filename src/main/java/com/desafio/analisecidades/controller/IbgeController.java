package com.desafio.analisecidades.controller;

import com.desafio.analisecidades.response.ibge.EstadoDTO;
import com.desafio.analisecidades.response.ibge.MunicipioDTO;
import com.desafio.analisecidades.service.IbgeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class IbgeController {

    private final IbgeService ibgeService;

    public IbgeController(IbgeService ibgeService) {
        this.ibgeService = ibgeService;
    }

    @GetMapping("/estados")
    @Operation(summary = "Lista todos os estados do Brasil")
    public List<EstadoDTO> getEstados(){
        return ibgeService.listarEstado();
    }

    @GetMapping("/municipios/estados/{uf}")
    @Operation(summary = "Lista municípios de um estado", description = "Por padrão retorna as 20 primeiras cidades, pode ajustar com parâmetro 'limit'")
    public List<MunicipioDTO> listarMunicipios(
            @Parameter(description = "Sigla do estado, ex: MG")
            @PathVariable String uf,
            @Parameter(description = "Número máximo de municípios a retornar, default 20")
            @RequestParam(defaultValue = "20") int limit) {
        return ibgeService.listarMunicipio(uf)
                .stream()
                .limit(limit)
                .collect(Collectors.toList());
    }
}
