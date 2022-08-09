package com.hackerrank.weather.service;


import com.hackerrank.weather.resource.WeatherRequest;
import com.hackerrank.weather.resource.WeatherResponse;

import java.util.Date;
import java.util.List;

public interface WeatherService {

    WeatherResponse saveWeatherRecord(WeatherRequest Weather);

    List<WeatherResponse> findWeatherRecordsByCriteria(Date date, String cityCriteria, String[] sortBy);

    WeatherResponse findById(final Integer id);

}
