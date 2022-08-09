package com.hackerrank.weather.controller;

import com.hackerrank.weather.resource.WeatherRequest;
import com.hackerrank.weather.resource.WeatherResponse;
import com.hackerrank.weather.service.WeatherService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController()
@RequestMapping(path = "/weather")
@AllArgsConstructor
@ControllerAdvice
public class WeatherApiRestController {

    private final WeatherService weatherService;

    @PostMapping
    public ResponseEntity<WeatherResponse> submitWeatherRecord(@Valid @RequestBody WeatherRequest weather) {
        return new ResponseEntity<>(weatherService.saveWeatherRecord(weather), HttpStatus.CREATED);
    }

    @GetMapping
    public List<WeatherResponse> getWeatherByCriteria(
            @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @RequestParam(value = "city", required = false) String cityCriteria,
            @RequestParam(value = "sort", defaultValue = "id:ASC", required = false) String[] sortBy) {

        return weatherService.findWeatherRecordsByCriteria(date, cityCriteria, sortBy);
    }

    @GetMapping(path = "/{id}")
    public WeatherResponse getWeatherRecordById(@PathVariable final Integer id) {
        return weatherService.findById(id);

    }

    @ExceptionHandler(value = HttpClientErrorException.class)
    public ResponseEntity<Object> exception(HttpClientErrorException exception) {
        return new ResponseEntity<>("404 Not Found", HttpStatus.NOT_FOUND);
    }
}
