package com.library.librarydemo.service;

import com.library.librarydemo.config.ApplicationProperties;
import com.library.librarydemo.domain.WeatherInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {

    private final WebClient webClient;
    private final ApplicationProperties applicationProperties;

    public WeatherService(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
        this.webClient = WebClient.create(applicationProperties.getWeather().getBaseUrl());
    }

    private String buildQuery(String city) {
        return "/current.json?key=" +
            applicationProperties.getWeather().getApiKey() + "&q=" + city + "&aqi=no";
    }

    public Mono<WeatherInfo> query(String city) {
        String query = buildQuery(city);

        return webClient.get()
                        .uri(query)
                        .retrieve()
                        .bodyToMono(WeatherInfo.class);
    }

}
