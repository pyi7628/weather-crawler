package org.ajou.realcoding.weathercrawler.weathercrawler.api;

import org.ajou.realcoding.weathercrawler.weathercrawler.domain.CurrentWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OpenWeatherMapApiClient {
    private final String appid="8aa1c2e8f07d8458f24a5985518987ea";
    private final String openWeatherUrl="https://api.openweathermap.org/data/2.5/weather?q={cityName}&appid={appid}";
    @Autowired //이미 생성되어있음!
    RestTemplate restTemplate;

    public CurrentWeather requestCurrentWeather(String cityName)
    {

        CurrentWeather currentWeather = restTemplate.exchange(openWeatherUrl, HttpMethod.GET,null,CurrentWeather.class,cityName,appid).getBody();
        return currentWeather;
    }
}
