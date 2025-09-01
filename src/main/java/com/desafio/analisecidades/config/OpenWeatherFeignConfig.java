package com.desafio.analisecidades.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class OpenWeatherFeignConfig {

    // Injeta a chave da API a partir do application.yml
    @Value("${apis.openweather.key}")
    private String apiKey;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                // Adiciona o parâmetro 'appid' com a chave e 'units=metric' para Celsius
                template.query("appid", apiKey);
                template.query("units", "metric");
                template.query("lang", "pt_br");
            }
        };
    }
}

