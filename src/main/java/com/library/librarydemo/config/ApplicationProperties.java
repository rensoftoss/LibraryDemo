package com.library.librarydemo.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = true, ignoreInvalidFields = true)
public class ApplicationProperties {

    @Getter @Setter
    private Weather weather = new Weather();

    @Data
    public static class Weather {
        private String apiKey;
        private String baseUrl;
    }

}
