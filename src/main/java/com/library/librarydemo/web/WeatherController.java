package com.library.librarydemo.web;

import com.library.librarydemo.domain.WeatherInfo;
import com.library.librarydemo.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/weather")
@Slf4j
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Mono<WeatherInfo>> getCurrentWeather(@RequestParam("city") String city) {
        log.info("Get current weather: {}", city);
        return ResponseEntity.ok().body(weatherService.query(city));
    }

}
