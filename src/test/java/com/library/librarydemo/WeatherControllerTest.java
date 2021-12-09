package com.library.librarydemo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.library.librarydemo.domain.Current;
import com.library.librarydemo.domain.Location;
import com.library.librarydemo.domain.WeatherInfo;
import com.library.librarydemo.service.WeatherService;
import com.library.librarydemo.web.WeatherController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(WeatherController.class)
public class WeatherControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private WeatherService weatherService;

    private WeatherInfo createWeatherInfo() {
        Location location = Location.builder()
                                    .country("Germany")
                                    .region("Berlin")
                                    .city("Berlin")
                                    .build();

        Current current = Current.builder()
                                 .celsiusTemperature(-2.0f)
                                 .fahrenheitTemperature(28.4f)
                                 .build();

        return WeatherInfo.builder()
                          .location(location)
                          .current(current)
                          .build();
    }

    @Test
    public void shouldGetWeatherInfo() {
        WeatherInfo weatherInfo = createWeatherInfo();

        when(weatherService.query(any(String.class))).thenReturn(Mono.just(weatherInfo));

        webClient.get()
                 .uri("/weather?city=Berlin")
                 .exchange()
                 .expectStatus()
                 .isOk()
                 .expectBody()
                 .jsonPath("city").isEqualTo("Berlin");
    }


}
