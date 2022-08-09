package com.hackerrank.weather.service.mapper;

import com.hackerrank.weather.model.Weather;
import com.hackerrank.weather.resource.WeatherRequest;
import com.hackerrank.weather.resource.WeatherResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-08-09T11:14:59+0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class WeatherMapperImpl implements WeatherMapper {

    @Override
    public Weather of(WeatherRequest weatherRequest) {
        if ( weatherRequest == null ) {
            return null;
        }

        Weather weather = new Weather();

        weather.setDate( weatherRequest.getDate() );
        weather.setLat( weatherRequest.getLat() );
        weather.setLon( weatherRequest.getLon() );
        weather.setCity( weatherRequest.getCity() );
        weather.setState( weatherRequest.getState() );
        List<Double> list = weatherRequest.getTemperatures();
        if ( list != null ) {
            weather.setTemperatures( new ArrayList<Double>( list ) );
        }

        return weather;
    }

    @Override
    public WeatherResponse of(Weather weather) {
        if ( weather == null ) {
            return null;
        }

        WeatherResponse weatherResponse = new WeatherResponse();

        weatherResponse.setId( weather.getId() );
        weatherResponse.setDate( weather.getDate() );
        weatherResponse.setLat( weather.getLat() );
        weatherResponse.setLon( weather.getLon() );
        weatherResponse.setCity( weather.getCity() );
        weatherResponse.setState( weather.getState() );
        List<Double> list = weather.getTemperatures();
        if ( list != null ) {
            weatherResponse.setTemperatures( new ArrayList<Double>( list ) );
        }

        return weatherResponse;
    }
}
