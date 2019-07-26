package org.ajou.realcoding.weathercrawler.weathercrawler.controller;


import org.ajou.realcoding.weathercrawler.weathercrawler.domain.CurrentWeather;
import org.ajou.realcoding.weathercrawler.weathercrawler.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.util.List;

@RestController
public class WeatherController {
    @Autowired
    WeatherService weatherservice;
    @GetMapping("/weather-crawler/available-cities")
    public List<String> getAvailableCities() throws IOException{
        return weatherservice.loadAvailableCityNamesFromFile();
    }

    @GetMapping("weather-crawler/current-weather/by-city-name/{cityName}")
    public CurrentWeather getCurrentWeater(@PathVariable String cityName)
    {
        return weatherservice.getCurrentWeatherBycityname(cityName);
    }




}
