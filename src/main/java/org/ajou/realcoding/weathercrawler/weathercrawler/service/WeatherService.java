package org.ajou.realcoding.weathercrawler.weathercrawler.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.ajou.realcoding.weathercrawler.weathercrawler.api.OpenWeatherMapApiClient;
import org.ajou.realcoding.weathercrawler.weathercrawler.domain.CurrentWeather;
import org.ajou.realcoding.weathercrawler.weathercrawler.repository.CurrentWeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.io.File;

@Service
@Slf4j
public class WeatherService {


    List<String> cities =null;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    OpenWeatherMapApiClient openWeatherMapApiClient;

    @PostConstruct
    public List<String> loadAvailableCityNamesFromFile() throws IOException {
        File file = new File( "availableCityNames");
        return objectMapper.readValue(file, new TypeReference<List<String>>(){});

    }

   /* public CurrentWeather getCurrentWeatherBycityname(String cityName)
    {
        return openWeatherMapApiClient.requestCurrentWeather(cityName);
    }*/

    LinkedList<String> citiesQueue=new LinkedList<>();
    @Autowired
    CurrentWeatherRepository currentWeatherRepository;
    public CurrentWeather getCurrentWeatherBycityname(String cityName) {
        return currentWeatherRepository.findCurrentWeatherByCityName(cityName);
    }
    @Scheduled(fixedDelay = 2000L)//2초마다 delay를 주어 받아옴
    public void getCurrentWeatherPeriodcally() throws IOException
    {
        if(citiesQueue.isEmpty())
        {
            citiesQueue.addAll(loadAvailableCityNamesFromFile());//
        }
        String targetCity=citiesQueue.pop();
        citiesQueue.addLast(targetCity);

        CurrentWeather currentWeather = openWeatherMapApiClient.requestCurrentWeather(targetCity);
        currentWeatherRepository.insertCurrentWeather(currentWeather);///요기
        log.info("Current weather has been inserted successfully.");
    }
}
