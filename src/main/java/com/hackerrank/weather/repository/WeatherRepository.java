package com.hackerrank.weather.repository;

import com.hackerrank.weather.model.Weather;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {

    @Query(value = "select w from Weather w where lower(w.city) IN (?1)")
    List<Weather> findByCity(String[] cities, Sort sort);

    @Query(value = "select w from Weather w where w.date = (?1)")
    List<Weather> findAllByDate(Date date);
}
