package com.hackerrank.weather.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherRequest {

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date date;
    @NotNull
    private Float lat;
    @NotNull
    private Float lon;
    @NotNull
    private String city;
    @NotNull
    private String state;
    @NotNull
    private List<Double> temperatures;
}
