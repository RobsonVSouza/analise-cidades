package com.desafio.analisecidades.config;

import org.springframework.context.annotation.Bean;
import feign.Logger;

public class IbgeFeignConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
