package com.hackerrank.weather.service.mapper;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.resource.WeatherRequest;
import com.hackerrank.weather.resource.WeatherResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeatherMapper {

    Weather of(WeatherRequest weatherRequest);

    WeatherResponse of(Weather weather);
}
