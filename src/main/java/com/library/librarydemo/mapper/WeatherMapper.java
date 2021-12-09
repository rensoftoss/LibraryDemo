package com.library.librarydemo.mapper;

import com.library.librarydemo.domain.WeatherInfo;
import com.library.librarydemo.dto.WeatherDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WeatherMapper {

    WeatherMapper INSTANCE = Mappers.getMapper(WeatherMapper.class);

    @Mappings({
        @Mapping(source = "location.city", target = "city"),
        @Mapping(source = "current.celsiusTemperature", target = "temperature")
    })
    WeatherDto weatherInfoToWeatherDto(WeatherInfo book);

}
