package com.alextrandafir.fx.config;

import com.alextrandafir.fx.client.RatesClient;
import com.alextrandafir.fx.ws.CubeType;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2CollectionHttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class RatesClientConfig {

    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder();
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//        return builder.build();
        RestTemplate restTemplate = new RestTemplate();


        List<HttpMessageConverter<?>> converters = new ArrayList<>();
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_XML));
//        converters.add(converter);

//        converters.add(getJaxb2CollectionHttpMessageConverter());

        converters.add(new FormHttpMessageConverter());
        converters.add(new StringHttpMessageConverter());
        restTemplate.setMessageConverters(converters);
        return restTemplate;
    }

    @Bean(name = "marshallingJaxb2CollectionHttpMessageConverter")
    public Jaxb2RootElementHttpMessageConverter getJaxb2CollectionHttpMessageConverter() {

        Jaxb2RootElementHttpMessageConverter jaxb2RootElementHttpMessageConverter = new Jaxb2RootElementHttpMessageConverter();
//        jaxb2RootElementHttpMessageConverter.
//        jaxb2CollectionHttpMessageConverter.set
//        marshallingHttpMessageConverter.setMarshaller(getJaxb2Marshaller());
//        marshallingHttpMessageConverter.setUnmarshaller(getJaxb2Marshaller());
        return jaxb2RootElementHttpMessageConverter;
    }

    @Bean
    public Jaxb2Marshaller getJaxb2Marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setClassesToBeBound(CubeType.class);
        return jaxb2Marshaller;
    }

//    @Bean
//    public Jaxb2Marshaller marshaller(){
//        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//        marshaller.setContextPath("com.alextrandafir.fx.ws");
//        return marshaller;
//    }

//    @Bean
//    public RatesClient ratesClient(Jaxb2Marshaller marshaller){
//        RatesClient client = new RatesClient();
//        client.setDefaultUri("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
//        client.setMarshaller(marshaller);
//        client.setUnmarshaller(marshaller);
//        return client;
//    }
}
