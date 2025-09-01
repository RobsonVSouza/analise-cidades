package com.desafio.analisecidades.service;

import com.desafio.analisecidades.response.AnaliseEstadoResponse;
import com.desafio.analisecidades.response.ibge.EstadoDTO;
import com.desafio.analisecidades.response.ibge.MunicipioDTO;
import com.desafio.analisecidades.response.tempo.TemperaturaDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class AnaliseService {

    private static final Logger logger = LoggerFactory.getLogger(AnaliseService.class);

    private final IbgeService ibgeService;
    private final WeatherService weatherService;

    public AnaliseService(IbgeService ibgeService, WeatherService weatherService) {
        this.ibgeService = ibgeService;
        this.weatherService = weatherService;
    }

    public AnaliseEstadoResponse analisarEstado(String sigla) {
        logger.info("Iniciando análise para o estado com sigla: {}", sigla);

        // Busca o estado pela sigla
        List<EstadoDTO> estados = ibgeService.listarEstado();
        EstadoDTO estado = estados.stream()
                .filter(e -> e.getSigla().equalsIgnoreCase(sigla))
                .findFirst()
                .orElseThrow(() -> {
                    logger.warn("Estado não encontrado para a sigla: {}", sigla);
                    return new RuntimeException("Estado não encontrado");
                });

        List<MunicipioDTO> municipios = ibgeService.listarMunicipio(sigla)
                .stream()
                .limit(20) // Limite de 20 cidades
                .collect(Collectors.toList());

        // Lista municípios da UF
       // List<MunicipioDTO> municipios = ibgeService.listarMunicipio(sigla);
        if (municipios.isEmpty()) {
            logger.warn("Nenhum município encontrado para o estado: {}", sigla);
        }

        // Consulta temperatura de todas as cidades em paralelo, pulando erros
        List<CompletableFuture<TemperaturaDto>> futures = municipios.stream()
                .map(m -> CompletableFuture.supplyAsync(() -> {
                    try {
                        return weatherService.getTemperaturaAtualPorCidade(m.getNome());
                    } catch (Exception e) {
                        logger.warn("Não foi possível buscar temperatura para {}: {}", m.getNome(), e.getMessage());
                        return null; // ignora erros e continua
                    }
                }))
                .collect(Collectors.toList());

        // Coleta resultados válidos
        List<TemperaturaDto> temperaturas = futures.stream()
                .map(CompletableFuture::join)
                .filter(t -> t != null)
                .collect(Collectors.toList());

        // Calcula cidade mais quente e mais fria
        Optional<TemperaturaDto> cidadeMaisQuente = temperaturas.stream()
                .max(Comparator.comparingDouble(TemperaturaDto::getTemperaturaAtual));

        Optional<TemperaturaDto> cidadeMaisFria = temperaturas.stream()
                .min(Comparator.comparingDouble(TemperaturaDto::getTemperaturaAtual));

        // Monta response
        AnaliseEstadoResponse response = new AnaliseEstadoResponse();
        response.setEstado(estado.getNome());
        response.setNumeroMunicipios(municipios.size());

        cidadeMaisQuente.ifPresent(t -> {
            response.setCidadeMaisQuente(t.getCidade());
            response.setTemperaturaMaisQuente(t.getTemperaturaAtual());
        });

        cidadeMaisFria.ifPresent(t -> {
            response.setCidadeMaisFria(t.getCidade());
            response.setTemperaturaMaisFria(t.getTemperaturaAtual());
        });

        logger.info("Análise concluída para o estado: {}", sigla);
        return response;
    }
}