package com.desafio.analisecidades.service;

import com.desafio.analisecidades.client.OpenWeatherApiClient;
import com.desafio.analisecidades.exception.CityNotFoundException;
import com.desafio.analisecidades.exception.WeatherServiceException;
import com.desafio.analisecidades.response.tempo.TemperaturaDto;
import com.desafio.analisecidades.response.tempo.WeatherResponse;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
    private static final String COUNTRY_CODE = ",br";

    private final OpenWeatherApiClient openWeatherApiClient;

    @Autowired
    public WeatherService(OpenWeatherApiClient openWeatherApiClient) {
        this.openWeatherApiClient = openWeatherApiClient;
    }

    public TemperaturaDto getTemperaturaAtualPorCidade(String city) {
        try {
            String cidadeEncoded = URLEncoder.encode(city + COUNTRY_CODE, StandardCharsets.UTF_8.toString());
            logger.info("Buscando temperatura para a cidade: {}", city);

            WeatherResponse responseDaApi = openWeatherApiClient.getWeatherByCity(cidadeEncoded);

            return new TemperaturaDto(responseDaApi.getName(), responseDaApi.getMain().getTemp());

        } catch (FeignException.NotFound nf) {
            // Cidade não encontrada → lança exceção customizada
            logger.warn("Cidade não encontrada na API do tempo: {}", city);
            throw new CityNotFoundException(city);

        } catch (UnsupportedEncodingException e) {
            logger.error("Erro de codificação ao buscar temperatura para cidade: {}", city, e);
            throw new WeatherServiceException("Erro de codificação ao buscar temperatura para cidade: " + city, e);

        } catch (FeignException fe) {
            // Qualquer outro erro do Feign (ex: 500, timeout)
            logger.error("Erro na chamada da API do tempo para cidade {}: {}", city, fe.getMessage());
            throw new WeatherServiceException("Erro ao buscar temperatura para cidade: " + city, fe);

        } catch (Exception e) {
            // Qualquer outro erro inesperado
            logger.error("Erro inesperado ao buscar temperatura para cidade: {}", city, e);
            throw new WeatherServiceException("Erro inesperado ao buscar temperatura para cidade: " + city, e);
        }
    }
}