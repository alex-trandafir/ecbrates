package com.alextrandafir.fx.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RatesClientConfig {

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new FormHttpMessageConverter());
        converters.add(new StringHttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        return restTemplate;
    }

    @Bean(name = "marshallingJaxb2CollectionHttpMessageConverter")
    public Jaxb2RootElementHttpMessageConverter getJaxb2CollectionHttpMessageConverter() {
        Jaxb2RootElementHttpMessageConverter jaxb2RootElementHttpMessageConverter = new Jaxb2RootElementHttpMessageConverter();
        return jaxb2RootElementHttpMessageConverter;
    }
}
