package com.desafio.analisecidades.client;

import com.desafio.analisecidades.config.OpenWeatherFeignConfig;
import com.desafio.analisecidades.response.tempo.WeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        contextId = "OpenWeather",
        name = "${apis.openweather.name}",
        url = "${apis.openweather.url}",
        configuration = OpenWeatherFeignConfig.class)
@Validated
public interface OpenWeatherApiClient {

    @GetMapping("/weather")
    WeatherResponse getWeatherByCity(@RequestParam("q") String city);
}
