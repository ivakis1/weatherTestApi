package com.hackerrank.weather.service;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.repository.WeatherRepository;
import com.hackerrank.weather.resource.WeatherRequest;
import com.hackerrank.weather.resource.WeatherResponse;
import com.hackerrank.weather.service.mapper.WeatherMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class WeatherServiceImpl implements WeatherService {

    private final String SORT_CRITERIA_DATE_ASC = "date";
    private final String SORT_CRITERIA_DATE_DESC = "-date";

    private final WeatherRepository repository;
    private final WeatherMapper weatherMapper;


    @Override
    public WeatherResponse saveWeatherRecord(final WeatherRequest weatherRequest) {
        log.info("Weather request: " + weatherRequest);

        final Weather record = repository.save(weatherMapper.of(weatherRequest));

        log.info("Successfully persisted weather record");

        return weatherMapper.of(record);
    }

    @Override
    public List<WeatherResponse> findWeatherRecordsByCriteria(final Date date,
                                                              final String cityCriteria,
                                                              final String[] sortBy) {
        log.info("Fetching weather records");

        List<Weather> records;
        final Sort sort = parseSortCriteria(sortBy);

        if (date != null) {
            records = repository.findAllByDate(date);
        } else if (cityCriteria != null && cityCriteria.length() > 0) {
            records = repository.findByCity(Arrays.stream(cityCriteria.split(","))
                    .map(String::toLowerCase).toArray(String[]::new), sort);
        } else {
            records = repository.findAll(parseSortCriteria(sortBy));
        }

        log.info("Fetched weather records");

        return records.stream().map(weatherMapper::of).collect(Collectors.toList());
    }

    @Override
    public WeatherResponse findById(final Integer id) {
        Optional<Weather> weather = repository.findById(id);

        if (weather.isPresent()) {
            return weatherMapper.of(weather.get());
        }

        throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    }

    private Sort parseSortCriteria(final String[] sortBy) {
        if (sortBy.length > 0) {
            if (SORT_CRITERIA_DATE_ASC.equals(sortBy[0])) {
                return Sort.by("date").ascending();
            } else if (SORT_CRITERIA_DATE_DESC.equals(sortBy[0])) {
                return Sort.by("date").descending();
            }
        }

        return Sort.by("id").ascending();
    }
}
