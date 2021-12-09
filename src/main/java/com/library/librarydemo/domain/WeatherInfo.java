package com.library.librarydemo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeatherInfo {

    private Location location;
    private Current current;

}
