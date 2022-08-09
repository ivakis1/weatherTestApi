package com.hackerrank.weather.resource;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class WeatherResponse {

    private Integer id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Float lat;
    private Float lon;
    private String city;
    @JsonAlias(value = "region")
    private String state;
    private List<Double> temperatures;
}
