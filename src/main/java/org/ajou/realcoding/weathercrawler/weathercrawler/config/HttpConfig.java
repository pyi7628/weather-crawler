package org.ajou.realcoding.weathercrawler.weathercrawler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration//스프링에게 객체화 요청
public class HttpConfig {

    @Bean //한번만 생성 후 이 객체를 스프링에 넣어서 보관하여 사용,한번만 new로!!
    public RestTemplate createRestTemplate(){
        return new RestTemplate();
    }
}
